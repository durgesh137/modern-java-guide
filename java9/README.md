# Module: java9

Targets Java release: 9

How to build

- Build only this module (use toolchains if your JDK doesn't match):

  mvn -pl java9 -am -DskipTests verify

- To run the simple Main (if present):

  java -jar java9/target/java9-0.0.1-SNAPSHOT.jar

Appendix: Required JDK & toolchains

- Required JDK: Java 9
- Recommended: install Java 9 locally or configure `~/.m2/toolchains.xml` to point to a Java 9 JDK.

Examples:

```bash
# build only this module (command-line)
mvn -pl java9 -am -DskipTests clean package

# build using the profile added to the parent POM
mvn -Pbuild-java9 clean package

# preview generated toolchains.xml (macOS helper)
./scripts/generate-toolchains-macos.sh --dry-run

# create ~/.m2/toolchains.xml interactively (backs up existing file)
./scripts/generate-toolchains-macos.sh --apply
```

Notes:
- If you use the `release` compiler flag (the default in this repo), make sure Maven runs with a JDK version that supports that `--release` value or configure toolchains to supply the correct JDK to the compiler plugin.
- See `docs/TOOLCHAINS.md` for more details.

Notes
- This module's `pom.xml` sets `<release>` to `9`. If your local JDK is older than this release, configure Maven Toolchains (`~/.m2/toolchains.xml`) with a JDK that matches or install the required JDK locally.
- Example toolchains file: `docs/example-toolchains.xml`
