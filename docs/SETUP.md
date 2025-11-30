# Setup Guide

Complete setup instructions for the modern-java-guide project.

> 🆘 **Having issues?** Check **[TROUBLESHOOTING.md](TROUBLESHOOTING.md)** for solutions!

## Quick Start

### 1. Work on a Single Module (Recommended)

You only need the JDK for the module you're working on.

**Option A: Use IntelliJ IDEA**
1. Open the project in IntelliJ
2. Right-click on the module you want to work on (e.g., `java8`)
3. Select "Mark Directory as" → "Excluded" for modules you don't need
4. Set your Project SDK to match the module's Java version
5. Build and run the module

**Option B: Command Line (with profile)**
```bash
# Build only java8 module
mvn -Pbuild-java8 clean package

# Build only java11 module  
mvn -Pbuild-java11 clean package

# Build only java17 module
mvn -Pbuild-java17 clean package
```

**Option C: Command Line (direct)**
```bash
# Build a specific module directly
cd java8
mvn clean package

# Or from root using -pl flag
mvn -pl java8 clean package
```

### 2. Set the Right JDK in IntelliJ

For each module you work on:

1. **File → Project Structure → Project**
   - Set "SDK" to the Java version matching your module (e.g., Java 8 for java8 module)

2. **File → Project Structure → Modules**
   - Select your module (e.g., java8)
   - Set "Module SDK" to match (e.g., Java 1.8)

3. **Settings → Build, Execution, Deployment → Build Tools → Maven → Runner**
   - Set "JRE" to match your module's Java version

### 3. Download Missing JDKs (Only When Needed)

IntelliJ makes this easy:

1. **File → Project Structure → SDKs**
2. Click the **+** button
3. Select **Download JDK...**
4. Choose vendor (recommend: Temurin/Adoptium or Corretto)
5. Select version (e.g., 8, 11, 17, 21)
6. Click **Download**

## Project Structure

```
modern-java-guide/
├── pom.xml              # Parent POM (optional, for multi-module builds)
├── java8/               # Java 8 module (independent)
│   ├── pom.xml
│   ├── src/
│   └── README.md
├── java11/              # Java 11 module (independent)
│   ├── pom.xml
│   ├── src/
│   └── README.md
├── java17/              # Java 17 module (independent)
│   ├── pom.xml
│   ├── src/
│   └── README.md
... (and so on for java9, java10, java12-java25)
```

## Key Features

✅ **No mandatory toolchains.xml** - no complex setup required
✅ **Independent modules** - work on java8 without needing java17 installed
✅ **Flexible** - use `<source>/<target>` compilation (works with any JDK >= target version)
✅ **Optional profiles** - convenient shortcuts but not required
✅ **IntelliJ-friendly** - download JDKs directly from IDE

## Build Scenarios

### Scenario 1: I only have Java 17 installed, want to work on java8 module

**Solution**: 
- Java 17 can compile Java 8 bytecode using `-source 1.8 -target 1.8`
- Just set IntelliJ module SDK to Java 17
- Build with: `mvn -Pbuild-java8 clean package`
- ✅ Works!

### Scenario 2: I want to work on java21 but only have Java 17

**Solution**:
- Download Java 21 from IntelliJ (File → Project Structure → SDKs → Download JDK)
- Or use Homebrew: `brew install --cask temurin21`
- Set module SDK to Java 21
- Build with: `mvn -Pbuild-java21 clean package`

### Scenario 3: I want to build all modules

**Not recommended** unless you need it. But if you do:
```bash
# This requires all JDKs (8, 9, 10, 11, ..., 25) installed
mvn clean package
```

Most people should work on one module at a time.

## Troubleshooting

### "Cannot find symbol" errors
- Make sure your IntelliJ Project SDK matches the module's Java version
- Reload Maven: Maven tool window → right-click project → Reload

### "release version X not supported"
- This means Maven is running with a JDK older than the target
- Solution: Set Maven Runner JRE to a newer JDK in IntelliJ settings

### Module builds but IDE shows errors
- File → Invalidate Caches / Restart
- Reimport Maven project (Maven tool window → Reload)

## Recommended Workflow

1. **Pick a module** to work on (e.g., java11)
2. **Ensure you have that JDK** (download if needed)
3. **Set IntelliJ SDK** to that version
4. **Work, code, learn** Java 11 features
5. **Build**: `mvn -Pbuild-java11 clean package` or right-click module → Maven → package
6. **Run**: Use IntelliJ run configurations with the correct JDK

## Advanced: Maven Toolchains (Optional)

If you have multiple JDKs and want strict version enforcement, you can use toolchains:

1. Generate toolchains.xml:
   ```bash
   ./scripts/generate-toolchains-macos.sh --dry-run
   ./scripts/generate-toolchains-macos.sh --apply
   ```

2. This creates `~/.m2/toolchains.xml` with all detected JDKs

But **this is optional** - the project works fine without it!

## Summary

- ✅ **Simple**: No complex setup, just install JDK you need
- ✅ **Flexible**: Work on any module independently  
- ✅ **Practical**: Focus on learning Java features, not tooling
- ✅ **IntelliJ-friendly**: Download JDKs from IDE as needed

Happy coding! 🚀

