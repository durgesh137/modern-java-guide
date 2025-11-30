# Maven Toolchains — example and generator

This document explains how to use Maven Toolchains with this multi-module project and how to generate a `~/.m2/toolchains.xml` on macOS using the provided script `scripts/generate-toolchains-macos.sh`.

Why use toolchains?
- Maven runs using a JDK (the one on PATH or configured in IDE), but you may need to compile individual modules with different JDK releases (for example `java8` needs Java 8 `javac`). Toolchains allow Maven plugins to pick a specific JDK installation for compilation.

Files in this repo
- `docs/example-toolchains.xml` — an example `~/.m2/toolchains.xml` you can copy and adapt.
- `scripts/generate-toolchains-macos.sh` — a safe generator script that discovers JDKs installed under common macOS locations and outputs a `toolchains.xml` you can preview and apply.

Quick steps
1) Preview detected JDKs and print the toolchains.xml to stdout:

```bash
./scripts/generate-toolchains-macos.sh --dry-run
```

2) Create `~/.m2/toolchains.xml` interactively (backs up existing file):

```bash
./scripts/generate-toolchains-macos.sh --apply
```

3) Force overwrite without prompt:

```bash
./scripts/generate-toolchains-macos.sh --apply --force
```

4) If you prefer manual edits: copy `docs/example-toolchains.xml` to `~/.m2/toolchains.xml` and update `<jdkHome>` paths.

Using the toolchains with Maven
- The parent `pom.xml` includes `maven-toolchains-plugin` in pluginManagement. To use it in a module you can enable the plugin in that module's `pom.xml` or rely on the parent's configuration. The per-module profiles added to the parent (`build-java8`, ...) set the `<maven.compiler.release>` property; ensure the running JDK supports that release or use toolchains to point to the correct JDK.

Notes
- The generator is macOS-focused and searches `/Library/Java/JavaVirtualMachines`, Homebrew locations, and uses `/usr/libexec/java_home -V`.
- The script is conservative: it creates backups when overwriting and asks for confirmation unless `--force` is used.

If you want, I can extend the script to support Linux, Windows (WSL), or to write an interactive GUI prompt. Let me know which additional platforms to support.

