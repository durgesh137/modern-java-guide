# Module: java8

This module targets Java 8 and uses the centralized compiler configuration from the root `pom.xml`.

If your IDE shows duplicate plugin entries (for example `maven-compiler-plugin[2]`) or stale Maven configuration, refresh the Maven project to force the IDE to re-read the effective POM.

Quick refresh steps

- Command line (sanity check):

```bash
# build only this module and its dependencies
./mvnw -pl java8 -am -DskipTests verify
```

- IntelliJ IDEA:
  1. Open the Maven tool window (View → Tool Windows → Maven).
  2. Click the Refresh button (↻) to re-import the Maven project.
  3. If stale entries remain: File → Invalidate Caches / Restart → Invalidate and Restart, then re-import.

- Eclipse / STS:
  1. Right-click the project → Maven → Update Project...
  2. Select the project(s), check "Force Update of Snapshots/Releases" and click OK.
  3. Optionally: Project → Clean...

- VS Code (with Maven extension):
  1. Open Command Palette (Cmd+Shift+P) → "Maven: Update All Projects".
  2. Or use the Maven sidebar and press the refresh button for the project.

Inspect the effective POM

If you want to verify where the compiler plugin is defined and what `<release>` value is used for this module, generate the effective POM and inspect pluginManagement:

```bash
mvn -pl java8 help:effective-pom -Doutput=/tmp/effective-java8-pom.xml
sed -n '/<pluginManagement>/,/<\/pluginManagement>/p' /tmp/effective-java8-pom.xml
# or search for the <release> value
grep -n '<release>' /tmp/effective-java8-pom.xml || true
```

If you want, I can add a short repo-level troubleshooting note or automate an IDE refresh script for common environments.

Appendix: Required JDK & toolchains

- Required JDK: Java 8
- Recommended: install Java 8 locally or configure `~/.m2/toolchains.xml` to point to a Java 8 JDK.

Examples:

```bash
# build only this module (command-line)
mvn -pl java8 -am -DskipTests clean package

# build using the profile added to the parent POM
mvn -Pbuild-java8 clean package

# preview generated toolchains.xml (macOS helper)
./scripts/generate-toolchains-macos.sh --dry-run

# create ~/.m2/toolchains.xml interactively (backs up existing file)
./scripts/generate-toolchains-macos.sh --apply
```

Notes:
- If you use the `release` compiler flag (the default in this repo), make sure Maven runs with a JDK version that supports that `--release` value or configure toolchains to supply the correct JDK to the compiler plugin.
- See `docs/TOOLCHAINS.md` for more details.
