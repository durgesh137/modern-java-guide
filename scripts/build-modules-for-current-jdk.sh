#!/usr/bin/env bash
set -euo pipefail

# Build only modules that target a Java release <= the currently running Java.
# Usage: ./scripts/build-modules-for-current-jdk.sh

JAVA_OUT=$(java -version 2>&1 | head -n1)
# java -version formats vary: openjdk version "17.0.2" 2023-01-17
# extract first number sequence
if [[ $JAVA_OUT =~ ([0-9]+)\. ]]; then
  MAJOR=${BASH_REMATCH[1]}
else
  # fallback: try to parse 'version "17' pattern
  if [[ $JAVA_OUT =~ "([0-9]+)" ]]; then
    MAJOR=${BASH_REMATCH[1]}
  else
    echo "Could not determine Java major version from: $JAVA_OUT" >&2
    exit 1
  fi
fi

echo "Detected Java major version: $MAJOR"

# Build list of modules whose numeric suffix <= MAJOR
MODULES=()
for v in {8..25}; do
  if (( v <= MAJOR )); then
    MODULES+=("java${v}")
  fi
done

if [ ${#MODULES[@]} -eq 0 ]; then
  echo "No modules to build for Java $MAJOR"
  exit 0
fi

MODULE_LIST=$(IFS=, ; echo "${MODULES[*]}")
echo "Building modules: ${MODULE_LIST}"

# Use Maven to build the selected modules and their dependencies
./mvnw -pl "$(IFS=,; echo "${MODULES[*]}")" -am -DskipTests verify

