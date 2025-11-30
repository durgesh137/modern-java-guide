# Module: java17

Targets Java release: 17

This module contains the Spring Boot sample application copied from the original project root.

How to build

- Build the module (if your JDK is 17 or you have toolchains configured):

  mvn -pl java17 -am -DskipTests verify

- Run the Spring Boot application (after building):

  mvn -pl java17 spring-boot:run

Notes
- This module's `pom.xml` sets `<release>` to `17`. If your local JDK is older than this release, configure Maven Toolchains (`~/.m2/toolchains.xml`) with a JDK that matches or install the required JDK locally.
- Example toolchains file: `docs/example-toolchains.xml`

