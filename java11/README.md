# Module: java11

Targets Java release: 11

How to build

- Build only this module (use toolchains if your JDK doesn't match):

  mvn -pl java11 -am -DskipTests verify

- To run the simple Main (if present):

  java -jar java11/target/java11-0.0.1-SNAPSHOT.jar

Appendix: Required JDK & toolchains

- Required JDK: Java 11
- Recommended: install Java 11 locally or configure `~/.m2/toolchains.xml` to point to a Java 11 JDK.

Examples:

```bash
# build only this module (command-line)
mvn -pl java11 -am -DskipTests clean package

# build using the profile added to the parent POM
mvn -Pbuild-java11 clean package

# preview generated toolchains.xml (macOS helper)
./scripts/generate-toolchains-macos.sh --dry-run

# create ~/.m2/toolchains.xml interactively (backs up existing file)
./scripts/generate-toolchains-macos.sh --apply
```

Notes:
- If you use the `release` compiler flag (the default in this repo), make sure Maven runs with a JDK version that supports that `--release` value or configure toolchains to supply the correct JDK to the compiler plugin.
- See `docs/TOOLCHAINS.md` for more details.

Notes
- This module's `pom.xml` sets `<release>` to `11`. If your local JDK is older than this release, configure Maven Toolchains (`~/.m2/toolchains.xml`) with a JDK that matches or install the required JDK locally.
- Example toolchains file: `docs/example-toolchains.xml`
