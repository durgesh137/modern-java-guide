#!/usr/bin/env bash
# Safe generator for ~/.m2/toolchains.xml on macOS
# Usage:
#   ./scripts/generate-toolchains-macos.sh --dry-run
#   ./scripts/generate-toolchains-macos.sh --preview
#   ./scripts/generate-toolchains-macos.sh --apply    # interactive confirmation
#   ./scripts/generate-toolchains-macos.sh --apply --force  # no prompts

set -euo pipefail

OUT_FILE_DEFAULT="$HOME/.m2/toolchains.xml"
DRY_RUN=false
APPLY=false
FORCE=false
OUT_FILE="$OUT_FILE_DEFAULT"

print_usage() {
  cat <<'USAGE'
Usage: generate-toolchains-macos.sh [options]
Options:
  --dry-run        Print generated toolchains.xml to stdout (default behavior)
  --preview        Alias for --dry-run
  --apply          Write generated file to ~/.m2/toolchains.xml (asks for confirmation)
  --force          When used with --apply, overwrite without prompting and backup previous file
  --output <path>  Write to a custom path instead of ~/.m2/toolchains.xml
  -h, --help       Show this help

Examples:
  ./scripts/generate-toolchains-macos.sh --dry-run
  ./scripts/generate-toolchains-macos.sh --apply
  ./scripts/generate-toolchains-macos.sh --apply --force
USAGE
}

# Parse args
while [[ $# -gt 0 ]]; do
  case "$1" in
    --dry-run|--preview)
      DRY_RUN=true; shift ;;
    --apply)
      APPLY=true; shift ;;
    --force)
      FORCE=true; shift ;;
    --output)
      if [[ -z "${2-}" ]]; then echo "--output requires a path"; exit 2; fi
      OUT_FILE="$2"; shift 2;;
    -h|--help)
      print_usage; exit 0;;
    *)
      echo "Unknown argument: $1"; print_usage; exit 2;;
  esac
done

# Helper: discover JDK installs
discover_jdks() {
  declare -A seen
  local jdk_paths=()

  # 1) Use /usr/libexec/java_home -V to find JDKs
  if command -v /usr/libexec/java_home >/dev/null 2>&1; then
    # capture lines that look like paths
    while IFS= read -r line; do
      # extract path from the end of lines like: 1.8.0_472 (x86_64) "Eclipse Temurin" - "Eclipse Temurin 8" /Library/...
      if [[ "$line" =~ /Library/Java/JavaVirtualMachines/.*/Contents/Home ]] || [[ "$line" =~ /Users/[^/]+/Library/Java/JavaVirtualMachines/.*/Contents/Home ]] || [[ "$line" =~ /opt/homebrew/.*/Contents/Home ]] || [[ "$line" =~ /usr/local/.*/Contents/Home ]]; then
        # extract the path (last field after quotes)
        path=$(echo "$line" | grep -o '/[^ ]*Contents/Home' | tail -1)
        [[ -n "$path" && -d "$path" ]] || continue
        seen["$path"]=1
      fi
    done < <(/usr/libexec/java_home -V 2>&1 || true)
  fi

  # 2) Scan /Library/Java/JavaVirtualMachines
  if [[ -d "/Library/Java/JavaVirtualMachines" ]]; then
    for d in /Library/Java/JavaVirtualMachines/*; do
      [[ -d "$d/Contents/Home" ]] || continue
      seen["$d/Contents/Home"]=1
    done
  fi

  # 2b) Scan ~/Library/Java/JavaVirtualMachines (user-installed JDKs)
  if [[ -d "$HOME/Library/Java/JavaVirtualMachines" ]]; then
    for d in "$HOME/Library/Java/JavaVirtualMachines"/*; do
      [[ -d "$d/Contents/Home" ]] || continue
      seen["$d/Contents/Home"]=1
    done
  fi

  # 3) Homebrew casks (common locations)
  for d in /usr/local/opt/* /opt/homebrew/opt/*; do
    if [[ -d "$d/libexec/openjdk" ]]; then
      seen["$d/libexec/openjdk"]=1
    fi
  done

  # 4) Fallback: common /Library/Java paths
  for d in /Library/Java/JavaVirtualMachines/*; do
    [[ -d "$d/Contents/Home" ]] || continue
    seen["$d/Contents/Home"]=1
  done

  for p in "${!seen[@]}"; do jdk_paths+=("$p"); done
  # sort unique
  IFS=$'\n' read -r -d '' -a sorted < <(printf "%s\n" "${jdk_paths[@]}" | sort -u && printf '\0')
  echo "${sorted[@]}"
}

# Helper: detect major version and vendor
get_jdk_metadata() {
  local jdk_home="$1"
  local version_str vendor major
  if [[ -x "$jdk_home/bin/java" ]]; then
    version_str=$("$jdk_home/bin/java" -XshowSettings:properties -version 2>&1 | awk '/java.version/ {print $3; exit}') || true
    # fallback
    if [[ -z "$version_str" ]]; then
      version_str=$("$jdk_home/bin/java" -version 2>&1 | head -n1 | sed -E 's/.*"([^"]+)".*/\1/') || true
    fi
  else
    version_str="unknown"
  fi

  # compute major
  if [[ "$version_str" == 1.* ]]; then
    major=$(echo "$version_str" | sed -E 's/^1\.([0-9]+).*/\1/')
  else
    major=$(echo "$version_str" | sed -E 's/^([0-9]+).*/\1/')
  fi

  # vendor guess from path
  vendor="unknown"
  case "$jdk_home" in
    *temurin*|*eclipse-temurin*) vendor="temurin";;
    *adoptopenjdk*|*adopt*|*adoptium*) vendor="adoptopenjdk";;
    *zulu*|*azul*) vendor="zulu";;
    *liberica*|*bellsoft*) vendor="liberica";;
    *oracle*|*jdk1.8*|*jdk-8*) vendor="oracle";;
    *openjdk*) vendor="openjdk";;
  esac

  echo "$major|$vendor|$jdk_home"
}

# Build XML
build_xml() {
  local jdk_list=("$@")
  cat <<'XML_HEADER'
<?xml version="1.0" encoding="UTF-8"?>
<toolchains>
XML_HEADER

  for j in "${jdk_list[@]}"; do
    # parse metadata
    IFS='|' read -r major vendor path <<<"$(get_jdk_metadata "$j")"
    if [[ -z "$major" || "$major" == "" ]]; then
      continue
    fi
    # sanitize vendor
    if [[ -z "$vendor" ]]; then vendor="unknown"; fi
    cat <<XML_BLOCK
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>${major}</version>
      <vendor>${vendor}</vendor>
    </provides>
    <configuration>
      <jdkHome>${path}</jdkHome>
    </configuration>
  </toolchain>
XML_BLOCK
  done

  cat <<'XML_FOOTER'
</toolchains>
XML_FOOTER
}

# Main
jdks=()
read -r -a jdks <<< "$(discover_jdks)"
if [[ ${#jdks[@]} -eq 0 ]]; then
  echo "No JDKs detected in common macOS locations. Try installing JDKs or pass --output with a manual file path." >&2
  exit 1
fi

xml_content=$(build_xml "${jdks[@]}")

if [[ "$DRY_RUN" == "true" || "$APPLY" == "false" && "$DRY_RUN" == "false" ]]; then
  # default behavior when no args: dry-run
  echo "$xml_content"
  exit 0
fi

if [[ "$APPLY" == "true" ]]; then
  mkdir -p "$(dirname "$OUT_FILE")"
  if [[ -f "$OUT_FILE" ]]; then
    backup="$OUT_FILE.bak.$(date +%Y%m%d%H%M%S)"
    if [[ "$FORCE" == "true" ]]; then
      cp -p "$OUT_FILE" "$backup" || true
      echo "Backed up existing $OUT_FILE to $backup"
    else
      echo "Existing $OUT_FILE found. Backing up to $backup and asking for confirmation..."
      cp -p "$OUT_FILE" "$backup" || true
      echo "Backup created: $backup"
      read -r -p "Write new toolchains.xml to $OUT_FILE? (y/N) " ans
      if [[ ! "$ans" =~ ^[Yy]$ ]]; then
        echo "Aborted by user. Existing file preserved at $OUT_FILE and backup $backup"; exit 0
      fi
    fi
  fi

  # write file
  echo "$xml_content" > "$OUT_FILE"
  echo "Wrote $OUT_FILE"
  exit 0
fi

# If we reach here and nothing matched, print usage
print_usage
exit 2

