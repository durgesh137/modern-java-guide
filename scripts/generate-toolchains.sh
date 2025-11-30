#!/usr/bin/env bash
set -euo pipefail

# scripts/generate-toolchains.sh
# Interactive generator for ~/.m2/toolchains.xml on macOS (and similar).
# Features:
#  - Detect JDK installations using /usr/libexec/java_home and common locations
#  - Interactive selection of which JDKs to include
#  - --dry-run to print XML without writing
#  - --auto to include all detected JDKs without prompts
#  - Backups of existing ~/.m2/toolchains.xml
#  - --output to write to alternate path

DRY_RUN=0
AUTO=0
OUTPUT="${HOME}/.m2/toolchains.xml"

usage() {
  cat <<EOF
Usage: $(basename "$0") [--dry-run] [--auto|--yes] [--output <file>] [--help]

Options:
  --dry-run       Print the generated toolchains.xml to stdout and exit (no write)
  --auto, --yes   Non-interactive: include all discovered JDKs and write without prompting
  --output <file> Write to this file instead of ~/.m2/toolchains.xml
  --help          Show this help
EOF
}

while [[ ${#} -gt 0 ]]; do
  case "$1" in
    --dry-run) DRY_RUN=1; shift ;;
    --auto|--yes) AUTO=1; shift ;;
    --output) OUTPUT="$2"; shift 2 ;;
    -h|--help) usage; exit 0 ;;
    *) echo "Unknown argument: $1"; usage; exit 1 ;;
  esac
done

# Collect candidate JDK homes
declare -a CANDIDATES
declare -A SEEN

add_candidate() {
  local p="$1"
  [[ -z "$p" ]] && return
  [[ ! -d "$p" ]] && return
  # normalize path
  p=$(cd "$p" && pwd -P)
  if [[ -z "${SEEN[$p]:-}" ]]; then
    SEEN[$p]=1
    CANDIDATES+=("$p")
  fi
}

# Primary: /usr/libexec/java_home -V (macOS)
if command -v /usr/libexec/java_home >/dev/null 2>&1; then
  while IFS= read -r line; do
    # try to extract /.../Contents/Home
    if [[ $line =~ (/.*/Contents/Home) ]]; then
      add_candidate "${BASH_REMATCH[1]}"
    fi
  done < <(/usr/libexec/java_home -V 2>&1 || true)
fi

# Common macOS locations
for p in /Library/Java/JavaVirtualMachines/*/Contents/Home; do
  add_candidate "$p"
done
for p in /Users/*/Library/Java/JavaVirtualMachines/*/Contents/Home; do
  add_candidate "$p"
done

# Homebrew common locations (Apple Silicon and Intel)
for p in /opt/homebrew/Cellar/*/*/libexec/openjdk*/Contents/Home; do
  add_candidate "$p"
done
for p in /opt/homebrew/opt/*/libexec/openjdk*/Contents/Home; do
  add_candidate "$p"
done
for p in /usr/local/Cellar/*/*/libexec/openjdk*/Contents/Home; do
  add_candidate "$p"
done
for p in /usr/local/opt/*/libexec/openjdk*/Contents/Home; do
  add_candidate "$p"
done

# SDKMAN locations
for p in "$HOME/.sdkman/candidates/java"/*; do
  if [[ -d "$p" ]]; then
    # SDKMAN JDK home sometimes is the folder itself
    add_candidate "$p"
    add_candidate "$p/Contents/Home"
  fi
done

# Filter and probe candidates for a working java binary and version
declare -a ENTRIES

probe_candidate() {
  local home="$1"
  local java_bin="$home/bin/java"
  if [[ ! -x "$java_bin" ]]; then
    return 1
  fi
  local ver_out
  ver_out=$("$java_bin" -version 2>&1 | head -n1 || true)
  # parse major version
  local major=""
  if [[ $ver_out =~ \"1\.([0-9]+) ]]; then
    major=${BASH_REMATCH[1]}
  elif [[ $ver_out =~ \"([0-9]+)\. ]]; then
    major=${BASH_REMATCH[1]}
  elif [[ $ver_out =~ version\ "([0-9]+)" ]]; then
    major=${BASH_REMATCH[1]}
  fi
  # vendor heuristic
  local vendor="openjdk"
  if [[ $ver_out =~ "Oracle" ]]; then vendor="oracle"; fi
  if [[ $ver_out =~ "Azul" ]]; then vendor="azul"; fi
  if [[ -z "$major" ]]; then
    # try executing java -XshowSettings:properties
    ver_out=$("$java_bin" -XshowSettings:properties -version 2>&1 | grep -i "java.version" || true)
    if [[ $ver_out =~ ([0-9]+) ]]; then
      major=${BASH_REMATCH[1]}
    fi
  fi
  if [[ -z "$major" ]]; then
    return 1
  fi
  ENTRIES+=("$major|$vendor|$home")
  return 0
}

for p in "${CANDIDATES[@]:-}"; do
  probe_candidate "$p" || true
done

if [[ ${#ENTRIES[@]} -eq 0 ]]; then
  echo "No JDKs detected on this machine. Please install JDK(s) or run this script with --output to create a file manually."
  exit 1
fi

# Show detected JDKs
echo "Detected JDKs:"
idx=0
declare -a VERS
declare -a VEND
declare -a PATHS
for e in "${ENTRIES[@]}"; do
  IFS='|' read -r ver vend path <<< "$e"
  VERS[$idx]="$ver"
  VEND[$idx]="$vend"
  PATHS[$idx]="$path"
  printf "%3d) Java %s  (%s)\n" "$idx" "$ver" "$path"
  idx=$((idx+1))
done

select_indices=()
if (( AUTO == 1 )); then
  for i in "${!VERS[@]}"; do select_indices+=("$i"); done
else
  echo
  read -r -p "Enter indices to include (comma separated), 'all' to include all, or 'q' to quit: " sel
  if [[ "$sel" == "q" ]]; then
    echo "Aborted. No changes made."; exit 0
  fi
  if [[ "$sel" == "all" ]]; then
    for i in "${!VERS[@]}"; do select_indices+=("$i"); done
  else
    # parse comma separated indices
    IFS=',' read -r -a toks <<< "$sel"
    for t in "${toks[@]}"; do
      t=$(echo "$t" | sed 's/[^0-9]//g')
      if [[ -n "$t" && "$t" -ge 0 && "$t" -lt ${#VERS[@]} ]]; then
        select_indices+=("$t")
      fi
    done
  fi
fi

if [[ ${#select_indices[@]} -eq 0 ]]; then
  echo "No selections made. Exiting."; exit 1
fi

# Build XML
xml_output="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<toolchains>\n"
for idx in "${select_indices[@]}"; do
  ver=${VERS[$idx]}
  vend=${VEND[$idx]}
  path=${PATHS[$idx]}
  if (( AUTO == 0 )); then
    read -r -p "Label for this JDK (major version) [default: $ver]: " input_ver
    if [[ -n "$input_ver" ]]; then ver="$input_ver"; fi
    read -r -p "Vendor label [default: $vend]: " input_vend
    if [[ -n "$input_vend" ]]; then vend="$input_vend"; fi
  fi
  # escape XML basics
  esc_path=$(printf '%s' "$path" | sed -e 's/&/&amp;/g' -e "s/</\&lt;/g" -e "s/>/\&gt;/g")
  xml_output+="  <toolchain>\n    <type>jdk</type>\n    <provides>\n      <version>$ver</version>\n      <vendor>$vend</vendor>\n    </provides>\n    <configuration>\n      <jdkHome>$esc_path</jdkHome>\n    </configuration>\n  </toolchain>\n"
done
xml_output+="</toolchains>\n"

if (( DRY_RUN == 1 )); then
  echo
  echo "----- DRY RUN: Generated toolchains.xml -----"
  echo -e "$xml_output"
  echo "--------------------------------------------"
  exit 0
fi

# Write file: backup if exists
mkdir -p "$(dirname "$OUTPUT")"
if [[ -f "$OUTPUT" ]]; then
  bak="$OUTPUT.bak-$(date +%Y%m%dT%H%M%S)"
  cp "$OUTPUT" "$bak"
  echo "Existing $OUTPUT backed up to $bak"
fi

# confirm if not auto
if (( AUTO == 0 )); then
  echo
  echo "About to write toolchains to: $OUTPUT"
  read -r -p "Proceed and overwrite (y/N)? " conf
  if [[ ! "$conf" =~ ^[Yy]$ ]]; then
    echo "Aborted by user. No changes made."; exit 0
  fi
fi

# write
printf '%s' "$xml_output" > "$OUTPUT"
chmod 600 "$OUTPUT" || true

echo "Wrote toolchains to $OUTPUT"

# summary
echo "\nEntries written:"
for idx in "${select_indices[@]}"; do
  printf " - Java %s  (%s)\n" "${VERS[$idx]}" "${PATHS[$idx]}"
done

exit 0

