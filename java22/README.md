# Module: java22
- Example toolchains file: `docs/example-toolchains.xml`
- This module's `pom.xml` sets `<release>` to `22`. If your local JDK is older than this release, configure Maven Toolchains (`~/.m2/toolchains.xml`) with a JDK that matches or install the required JDK locally.
Notes

  java -jar java22/target/java22-0.0.1-SNAPSHOT.jar

- To run the simple Main (if present):

  mvn -pl java22 -am -DskipTests verify

- Build only this module (use toolchains if your JDK doesn't match):

How to build

Targets Java release: 22


