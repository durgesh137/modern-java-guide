# Troubleshooting Guide

Complete reference for all errors, issues, and solutions encountered in the modern-java-guide project.

**Last Updated:** November 30, 2025

---

## Table of Contents

1. [Build & Compilation Errors](#build--compilation-errors)
2. [Maven Configuration Errors](#maven-configuration-errors)
3. [JAR Execution Errors](#jar-execution-errors)
4. [IDE Issues](#ide-issues)
5. [JDK Version Issues](#jdk-version-issues)
6. [Plugin Errors](#plugin-errors)
7. [Common Solutions](#common-solutions)

---

## Build & Compilation Errors

### Error 1: Cannot find matching toolchain definitions

**Error Message:**
```
[ERROR] org.apache.maven.plugins:maven-toolchains-plugin:3.1.0:toolchain (default) on project modern-java-guide: 
Cannot find matching toolchain definitions for the following toolchain types:
[ERROR] jdk [ version='8' ]
[ERROR] Please make sure you define the required toolchains in your ~/.m2/toolchains.xml file.
```

**Cause:**
- Maven toolchains plugin is looking for a JDK entry in `~/.m2/toolchains.xml`
- File doesn't exist or doesn't have the required JDK version entry

**Solution:**
This error has been **FIXED** in the current project setup. The toolchains plugin is now optional.

If you still encounter it:

**Option A: Skip toolchains (Recommended for learning)**
```bash
# Build without toolchains
cd java8
mvn clean package

# Or use profiles
mvn -Pbuild-java8 clean package
```

**Option B: Create toolchains.xml (Advanced)**
```bash
# Generate toolchains file
./scripts/generate-toolchains-macos.sh --dry-run
./scripts/generate-toolchains-macos.sh --apply
```

**Status:** ✅ RESOLVED

---

### Error 2: ExceptionInInitializerError - Lombok Conflict

**Error Message:**
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) 
on project java8: Fatal error compiling: java.lang.ExceptionInInitializerError
com.sun.tools.javac.code.TypeTag :: UNKNOWN
```

**Cause:**
- Lombok annotation processor incompatibility with javac 25
- Lombok version conflicts across different Java versions

**Solution:**
This error has been **FIXED**. Lombok was removed from the parent POM.

If you need Lombok in a specific module:
1. Add Lombok dependency to that module's `pom.xml`
2. Use Lombok version appropriate for your Java version:
   - Java 8-11: Lombok 1.18.20
   - Java 17+: Lombok 1.18.30+

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

**Status:** ✅ RESOLVED

---

### Error 3: Invalid flag: --release (Java 8)

**Error Message:**
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) 
on project java8: Fatal error compiling: invalid flag: --release
```

**Cause:**
- The `--release` flag is not supported in Java 8
- `--release` flag was introduced in Java 9
- Using `maven.compiler.release` property triggers this flag

**Solution:**
This error has been **FIXED** in the java8 module (as of Nov 30, 2025).

The java8 module now uses `maven.compiler.source` and `maven.compiler.target` instead of `maven.compiler.release`.

**Fixed configuration in java8/pom.xml:**
```xml
<properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
</properties>
```

**If you encounter this in other modules or custom code:**

Replace:
```xml
<properties>
    <maven.compiler.release>8</maven.compiler.release>
</properties>
```

With:
```xml
<properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
</properties>
```

**Verify the fix:**
```bash
cd java8
mvn clean compile
# Should build successfully
```

**Status:** ✅ RESOLVED (Fixed in java8 module)

---

### Error 4: Release version X not supported

**Error Message:**
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) 
on project java12: Fatal error compiling: error: release version 12 not supported
```

**Cause:**
- Maven running with JDK older than the target release version
- Example: Maven uses JDK 11 but module targets Java 12

**Solutions:**

**Option A: Use a newer JDK for Maven**
```bash
# Check current Maven JDK
mvn -version

# Set JAVA_HOME to newer JDK (macOS)
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
mvn clean package
```

**Option B: In IntelliJ**
1. `Settings → Build, Execution, Deployment → Build Tools → Maven → Runner`
2. Set **JRE** to Java 17 or higher
3. Rebuild project

**Option C: Build only modules your JDK supports**
```bash
# Build only java8 with any JDK
mvn -Pbuild-java8 clean package
```

**Status:** ⚠️ Expected behavior - use compatible JDK

---

### Error 5: Source value X is obsolete

**Error Message:**
```
[WARNING] source value 8 is obsolete and will be removed in a future release
[WARNING] target value 8 is obsolete and will be removed in a future release
[WARNING] To suppress warnings about obsolete options, use -Xlint:-options.
```

**Cause:**
- Building with newer javac (e.g., Java 25) targeting older version (e.g., Java 8)
- Just a warning, not an error

**Solutions:**

**Option A: Ignore (Recommended)**
- This is just a warning
- Code compiles and runs correctly
- No action needed

**Option B: Suppress warnings**
```xml
<configuration>
    <source>1.8</source>
    <target>1.8</target>
    <compilerArgs>
        <arg>-Xlint:-options</arg>
    </compilerArgs>
</configuration>
```

**Option C: Use matching JDK version**
```bash
# Use Java 8 to build Java 8 module
export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
mvn clean package
```

**Status:** ⚠️ Warning only - safe to ignore

---

### Error 6: Implicitly declared classes not supported

**Error Message:**
```
[ERROR] /path/to/java18/Main.java:[5,19] implicitly declared classes are not supported in -source 18
  (use -source 25 or higher to enable implicitly declared classes)
```

**Cause:**
- Code uses Java 25 syntax (implicitly declared classes) in a Java 18 module
- Feature mismatch with target version

**Solution:**

**Fix the Main.java to use proper class syntax:**
```java
package com.modernjava.guide;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from Java 18!");
    }
}
```

**Status:** ⚠️ Code issue - fix module's Java code

---

## JAR Execution Errors

### Error 7: No main manifest attribute

**Error Message:**
```
no main manifest attribute, in target/java8-0.0.1-SNAPSHOT.jar
```

**Cause:**
- JAR doesn't have `Main-Class` entry in `META-INF/MANIFEST.MF`
- maven-jar-plugin not configured

**Solution:**
This error has been **FIXED** in java8 and java11 modules.

For other modules, add to `pom.xml`:

```xml
<build>
    <plugins>
        <!-- Inherit maven-jar-plugin from parent to create executable JAR -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

**Verify:**
```bash
# Check manifest
unzip -p target/java8-0.0.1-SNAPSHOT.jar META-INF/MANIFEST.MF | grep Main-Class

# Should show: Main-Class: com.modernjava.guide.Main
```

**Status:** ✅ RESOLVED (for java8, java11)

---

### Error 8: ClassNotFoundException when running JAR

**Error Message:**
```
Error: Could not find or load main class com.modernjava.guide.Main
Caused by: java.lang.ClassNotFoundException: com.modernjava.guide.Main
```

**Cause:**
- Main class doesn't exist
- Wrong package name in manifest
- Class file not included in JAR

**Solutions:**

**Option A: Verify Main class exists**
```bash
# Check if Main.java exists
ls -la src/main/java/com/modernjava/guide/Main.java

# Check if class is in JAR
unzip -l target/java8-0.0.1-SNAPSHOT.jar | grep Main.class
```

**Option B: Fix package name**
Ensure Main.java has correct package:
```java
package com.modernjava.guide;  // Must match

public class Main {
    public static void main(String[] args) {
        // ...
    }
}
```

**Option C: Rebuild**
```bash
mvn clean package
java -jar target/java8-0.0.1-SNAPSHOT.jar
```

**Status:** ⚠️ Check class path and rebuild

---

## IDE Issues

### Error 9: IntelliJ shows errors but Maven builds fine

**Symptoms:**
- Red underlines in IntelliJ
- "Cannot find symbol" errors
- Maven builds successfully from command line

**Causes:**
- IntelliJ cache issues
- Project SDK mismatch
- Maven not synced

**Solutions:**

**Option A: Invalidate caches (Most common fix)**
1. `File → Invalidate Caches / Restart`
2. Select "Invalidate and Restart"
3. Wait for re-indexing

**Option B: Reload Maven project**
1. Open Maven tool window (View → Tool Windows → Maven)
2. Click the Reload button (↻)
3. Wait for import to complete

**Option C: Check Project SDK**
1. `File → Project Structure → Project`
2. Set **SDK** to appropriate Java version (e.g., 17)
3. Click OK

**Option D: Reimport all modules**
1. Close project
2. Delete `.idea` folder (optional)
3. Reopen project
4. Maven will auto-import

**Status:** ⚠️ IDE issue - try cache invalidation

---

### Error 10: Module SDK vs Project SDK mismatch

**Symptoms:**
- Different modules show different errors
- Some modules compile, others don't
- IDE uses wrong Java version

**Solution:**

**Set SDK per module:**
1. `File → Project Structure → Modules`
2. Select module (e.g., java8)
3. Set **Module SDK** to match module's Java version
4. Repeat for each module you're working on

**Or set all to one JDK:**
1. `File → Project Structure → Project`
2. Set **SDK** to Java 17 (can compile older versions)
3. All modules inherit this SDK

**Status:** ⚠️ Configuration issue

---

### Error 11: Maven tool window shows duplicate plugins

**Symptoms:**
- See `maven-compiler-plugin [1]` and `maven-compiler-plugin [2]`
- Plugins appear multiple times in effective POM

**Cause:**
- Maven cache issue
- IntelliJ not reading effective POM correctly

**Solution:**

```bash
# Clean Maven cache and rebuild
mvn clean
rm -rf ~/.m2/repository/com/modernjava

# Reload in IntelliJ
# Maven tool window → right-click project → Reload
```

**Status:** ⚠️ Cache issue - reload Maven

---

## JDK Version Issues

### Error 12: JDK X not found / not installed

**Symptoms:**
- "Cannot find JDK for version X"
- Module won't build
- SDK dropdown empty

**Solutions:**

**Option A: Download in IntelliJ (Easiest)**
1. `File → Project Structure → SDKs`
2. Click **+** → **Download JDK**
3. Select vendor: Temurin or Corretto
4. Select version: 8, 11, 17, 21, etc.
5. Click **Download**

**Option B: Homebrew (macOS)**
```bash
brew install --cask temurin8
brew install --cask temurin11
brew install --cask temurin17
brew install --cask temurin21

# Verify
/usr/libexec/java_home -V
```

**Option C: SDKMAN**
```bash
# Install SDKMAN if not installed
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install JDKs
sdk install java 8.0.392-tem
sdk install java 11.0.21-tem
sdk install java 17.0.9-tem
sdk install java 21.0.1-tem
```

**Option D: Manual download**
- Eclipse Temurin: https://adoptium.net/
- Amazon Corretto: https://aws.amazon.com/corretto/
- Oracle JDK: https://www.oracle.com/java/technologies/downloads/

**Status:** ⚠️ Install required JDK

---

### Error 13: Wrong JDK used at runtime

**Symptoms:**
- Built for Java 8 but runs on Java 25
- `java.version` shows unexpected version

**Explanation:**
- This is **expected behavior**
- Java 25 can run Java 8 bytecode (backward compatible)
- JDK used at runtime is based on `java` command in PATH

**To use specific JDK:**

```bash
# Check current java
which java
java -version

# Use specific JDK (macOS)
export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
export PATH="$JAVA_HOME/bin:$PATH"

# Verify
java -version

# Run JAR with that JDK
java -jar target/java8-0.0.1-SNAPSHOT.jar
```

**Or run directly with full path:**
```bash
/Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home/bin/java \
  -jar target/java8-0.0.1-SNAPSHOT.jar
```

**Status:** ⚠️ Expected - use specific JDK if needed

---

## Plugin Errors

### Error 14: Plugin execution not covered by lifecycle

**Error Message:**
```
Plugin execution not covered by lifecycle configuration: 
org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile
```

**Cause:**
- Eclipse M2E plugin issue
- IntelliJ rarely shows this

**Solution:**

**If in Eclipse:**
Add to `pom.xml`:
```xml
<pluginManagement>
    <plugins>
        <plugin>
            <groupId>org.eclipse.m2e</groupId>
            <artifactId>lifecycle-mapping</artifactId>
            <version>1.0.0</version>
            <configuration>
                <lifecycleMappingMetadata>
                    <pluginExecutions>
                        <pluginExecution>
                            <pluginExecutionFilter>
                                <groupId>org.apache.maven.plugins</groupId>
                                <artifactId>maven-compiler-plugin</artifactId>
                                <versionRange>[3.0.0,)</versionRange>
                                <goals>
                                    <goal>compile</goal>
                                </goals>
                            </pluginExecutionFilter>
                            <action>
                                <execute />
                            </action>
                        </pluginExecution>
                    </pluginExecutions>
                </lifecycleMappingMetadata>
            </configuration>
        </plugin>
    </plugins>
</pluginManagement>
```

**Status:** ⚠️ Eclipse-specific - add lifecycle mapping

---

### Error 15: Maven Surefire plugin errors

**Error Message:**
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:X.X.X:test
```

**Causes:**
- Test failures
- Surefire version incompatibility

**Solutions:**

**Option A: Skip tests during build**
```bash
mvn clean package -DskipTests
```

**Option B: Fix test issues**
```bash
# Run tests with debug output
mvn test -X
```

**Option C: Update Surefire version**
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.5.2</version>
</plugin>
```

**Status:** ⚠️ Test-related - skip or fix tests

---

## Common Solutions

### Solution 1: Clean Build Everything

When in doubt, clean everything and rebuild:

```bash
# Clean Maven cache
rm -rf ~/.m2/repository/com/modernjava

# Clean project
mvn clean

# Rebuild specific module
cd java8
mvn clean package

# Or from root
mvn -Pbuild-java8 clean package
```

---

### Solution 2: Reset IntelliJ

Complete IntelliJ reset:

1. Close IntelliJ
2. Delete project's `.idea` folder
3. Delete `*.iml` files
4. Reopen project
5. `File → Invalidate Caches / Restart`
6. Wait for Maven reimport

---

### Solution 3: Verify Java Installation

Check your Java setup:

```bash
# List all installed JDKs (macOS)
/usr/libexec/java_home -V

# Check current java
which java
java -version

# Check JAVA_HOME
echo $JAVA_HOME

# Set JAVA_HOME (macOS)
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

---

### Solution 4: Maven Verbose Mode

Get detailed Maven output:

```bash
# Debug mode
mvn clean package -X

# Show stacktraces
mvn clean package -e

# Both
mvn clean package -X -e
```

---

### Solution 5: Check Effective POM

See the actual configuration Maven uses:

```bash
# Generate effective POM
mvn -pl java8 help:effective-pom -Doutput=effective-pom.xml

# View it
cat effective-pom.xml

# Or in IntelliJ
# Maven tool window → right-click module → Show Effective POM
```

---

### Solution 6: Dependency Issues

If you encounter dependency resolution issues:

```bash
# Update dependencies
mvn clean install -U

# Dependency tree
mvn dependency:tree

# Purge local repository
mvn dependency:purge-local-repository
```

---

## Quick Diagnostic Commands

Run these to gather diagnostic info:

```bash
# System info
uname -a

# Java info
java -version
javac -version
/usr/libexec/java_home -V  # macOS only

# Maven info
mvn -version
mvn -X --version  # verbose

# Project info
cd /path/to/modern-java-guide
mvn help:effective-pom -q
mvn help:effective-settings -q

# Module info
cd java8
mvn help:describe -Dplugin=compiler
mvn help:active-profiles
```

---

## Error Decision Tree

```
Build Failed?
├─ Toolchains error? → See Error 1
├─ Lombok error? → See Error 2
├─ Release flag error? → See Error 3
├─ Release not supported? → See Error 4
└─ Other compilation? → Run mvn -X

JAR won't run?
├─ No manifest? → See Error 7
├─ ClassNotFound? → See Error 8
└─ Other runtime? → Check JAVA_HOME

IDE issues?
├─ Red underlines? → See Error 9
├─ SDK mismatch? → See Error 10
└─ Duplicate plugins? → See Error 11

JDK issues?
├─ JDK not found? → See Error 12
├─ Wrong JDK used? → See Error 13
└─ Need to install? → See Solution in Error 12

Still stuck?
├─ Clean build → Solution 1
├─ Reset IDE → Solution 2
├─ Check Java → Solution 3
└─ Verbose Maven → Solution 4
```

---

## Getting Help

If you're still stuck after trying solutions above:

1. **Check module README**: Each module has specific instructions
2. **Read setup guide**: `SETUP.md` has detailed scenarios
3. **Run diagnostics**: Use commands in Quick Diagnostic Commands section
4. **Simplify**: Try building just one module in isolation
5. **Fresh start**: Clone project again, delete `.m2/repository/com/modernjava`

---

## Preventive Best Practices

To avoid common errors:

1. ✅ **One module at a time**: Don't try to build all modules
2. ✅ **Use profiles**: `mvn -Pbuild-java8 clean package`
3. ✅ **Match JDKs**: Use JDK >= target version
4. ✅ **Keep Maven updated**: `brew upgrade maven`
5. ✅ **Keep IntelliJ updated**: Latest version has fewer issues
6. ✅ **Clean builds**: Run `mvn clean` before troubleshooting
7. ✅ **Check documentation**: Read module READMEs first
8. ✅ **Isolate issues**: Test in clean environment

---

## Related Documentation

- **[../README.md](../README.md)** - Project overview
- **[SETUP.md](SETUP.md)** - Detailed setup guide
- **[QUICK_REF.md](QUICK_REF.md)** - Quick command reference
- **[GIT_BEST_PRACTICES.md](GIT_BEST_PRACTICES.md)** - Git & version control guide
- **Module READMEs** - `../java8/README.md`, `../java11/README.md`, etc.

---

## Changelog

### November 30, 2025
- ✅ Fixed toolchains error (made optional)
- ✅ Fixed Lombok conflicts (removed from parent)
- ✅ Fixed release flag error (use source/target)
- ✅ Fixed JAR execution error (added maven-jar-plugin)
- ✅ Simplified overall setup
- ✅ Created comprehensive documentation

---

**Last Updated:** November 30, 2025  
**Status:** Living document - will be updated as new issues are discovered

