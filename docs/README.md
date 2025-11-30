# Multi-module run instructions

This repo contains multiple modules each targeting a different Java release (java8..java25). Use the per-module README files for module-specific instructions. Key docs:

- `docs/TOOLCHAINS.md` — how to generate and use `~/.m2/toolchains.xml` on macOS.
- `docs/example-toolchains.xml` — example file to copy to `~/.m2/toolchains.xml`.
- `scripts/generate-toolchains-macos.sh` — script to discover local JDKs and generate a toolchains.xml (preview or apply).

Typical workflows

- Build a single module using the profile:
  mvn -Pbuild-java8 clean package

- Or build a single module from command line (no POM changes):
  mvn -pl java8 -am clean package

- If compilation fails because the running JDK doesn't support the module's <release>, either:
  - Install the required JDK and set Maven/IDE to use it; or
  - Configure `~/.m2/toolchains.xml` and enable the toolchains plugin for your module.

