# Module: java25

Targets Java release: 25

How to build

- Build only this module (use toolchains if your JDK doesn't match):

  mvn -pl java25 -am -DskipTests verify

- To run the simple Main (if present):

  java -jar java25/target/java25-0.0.1-SNAPSHOT.jar

Notes
- This module's `pom.xml` sets `<release>` to `25`. If your local JDK is older than this release, configure Maven Toolchains (`~/.m2/toolchains.xml`) with a JDK that matches or install the required JDK locally.
- Example toolchains file: `docs/example-toolchains.xml`

