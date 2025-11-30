# Modern Java Guide

A simple multi-module Maven project for learning and exploring Java features across different versions (Java 8 through Java 25).

> 🆘 **Having issues?** Check the **[Troubleshooting Guide](docs/TROUBLESHOOTING.md)** for solutions!

## 🎯 Project Goal

Learn Java version-specific features by working on independent modules - one for each Java version. Each module is self-contained and can be built/run independently.

## ✨ Key Features

- **✅ Simple Setup** - No complex toolchains required
- **✅ Independent Modules** - Work on java8 without needing java17 installed
- **✅ Flexible** - Use any JDK >= your target version
- **✅ IntelliJ-Friendly** - Download JDKs directly from IDE
- **✅ No Cross-Dependencies** - Each module stands alone

## 📁 Project Structure

```
modern-java-guide/
├── README.md              # Project overview (you are here)
├── pom.xml                # Parent POM
├── docs/
│   ├── SETUP.md           # Complete setup guide
│   ├── TROUBLESHOOTING.md # All errors & solutions
│   ├── GIT_BEST_PRACTICES.md # Git guide
│   ├── QUICK_REF.md       # Quick command reference
│   └── TOOLCHAINS.md      # Advanced Maven toolchains
├── java8/                 # Java 8 module
│   ├── pom.xml
│   ├── src/main/java/
│   └── README.md
├── java9-25/              # More Java modules
└── scripts/               # Helper scripts
```

## 🚀 Quick Start

### Option 1: Work on a Single Module (Recommended)

```bash
# Navigate to the module you want
cd java8

# Build it
mvn clean package

# Run it
java -jar target/java8-0.0.1-SNAPSHOT.jar
```

### Option 2: Build from Root

```bash
# Build only one module
mvn -pl java8 clean package

# Or use the convenience profile
mvn -Pbuild-java8 clean package
```

### Option 3: IntelliJ IDEA

1. Open the project in IntelliJ
2. Right-click on a module (e.g., `java8`)
3. Select **Run 'Main'**
4. Done!

## 📥 Installing JDKs

### In IntelliJ (Easiest)

1. **File → Project Structure → SDKs**
2. Click **+** → **Download JDK...**
3. Choose vendor (Temurin or Corretto recommended)
4. Select version (8, 11, 17, 21, etc.)
5. Click **Download**

### Via Homebrew (macOS)

```bash
brew install --cask temurin8    # Java 8
brew install --cask temurin11   # Java 11
brew install --cask temurin17   # Java 17
brew install --cask temurin21   # Java 21
```

### Via SDKMAN

```bash
sdk install java 8.0.392-tem
sdk install java 11.0.21-tem
sdk install java 17.0.9-tem
sdk install java 21.0.1-tem
```

## 📚 Modules

Each module targets a specific Java version:

| Module | Java Version | Key Features |
|--------|-------------|--------------|
| java8  | Java 8 (1.8) | Lambdas, Streams, Optional |
| java9  | Java 9      | Modules, JShell, Collection factories |
| java10 | Java 10     | Local variable type inference (var) |
| java11 | Java 11     | HTTP Client, String methods |
| java12 | Java 12     | Switch expressions (preview) |
| java13 | Java 13     | Text blocks (preview) |
| java14 | Java 14     | Records (preview), Pattern matching |
| java15 | Java 15     | Sealed classes (preview) |
| java16 | Java 16     | Records, Pattern matching (standard) |
| java17 | Java 17     | Sealed classes (standard), LTS |
| java18 | Java 18     | UTF-8 by default |
| java19 | Java 19     | Virtual threads (preview) |
| java20 | Java 20     | Scoped values (incubator) |
| java21 | Java 21     | Virtual threads, Sequenced collections, LTS |
| java22 | Java 22     | Unnamed variables |
| java23 | Java 23     | Primitive patterns |
| java24 | Java 24     | Latest features |
| java25 | Java 25     | Implicitly declared classes |

## 🛠️ How It Works

### Simplified Build Configuration

- **No mandatory toolchains** - Works with whatever JDK you have
- **Source/target compilation** - More flexible than `--release`
- **Parent POM** provides common configuration
- **Modules inherit** but can override settings

### Compiler Settings

Each module sets its target Java version:

```xml
<properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
</properties>
```

### Why This Works

- A newer JDK can compile for older targets using `-source` and `-target`
- Example: Java 17 can compile Java 8 bytecode
- You don't need Java 8 installed to work on the java8 module (though it's recommended)

## 🎓 Learning Workflow

1. **Pick a Java version** to learn (e.g., Java 17)
2. **Install that JDK** (use IntelliJ download or Homebrew)
3. **Open the module** (java17)
4. **Read the module README** for feature overview
5. **Code and experiment** with that version's features
6. **Build and run** to test your code
7. **Move to the next version** when ready

## 🔧 Troubleshooting

### "Cannot find symbol" errors

- Ensure your IDE Project SDK matches the module's target version
- Reload Maven project: **Maven tool window** → right-click → **Reload**

### "release version X not supported"

- Your Maven JDK is older than the target
- Set Maven runner to use a newer JDK: **Settings → Build Tools → Maven → Runner → JRE**

### Module builds but IDE shows errors

- **File → Invalidate Caches / Restart**
- Reimport Maven: **Maven tool window → Reload**

### More Issues?

📘 **See the complete [Troubleshooting Guide](docs/TROUBLESHOOTING.md)** for all errors and solutions!

## 📖 Documentation

- **[docs/SETUP.md](docs/SETUP.md)** - Complete setup instructions
- **[docs/TROUBLESHOOTING.md](docs/TROUBLESHOOTING.md)** - All errors & solutions
- **[docs/GIT_BEST_PRACTICES.md](docs/GIT_BEST_PRACTICES.md)** - Git & version control guide
- **[docs/QUICK_REF.md](docs/QUICK_REF.md)** - Quick command reference
- **[docs/TOOLCHAINS.md](docs/TOOLCHAINS.md)** - Optional Maven toolchains setup
- **Module READMEs** - Each module: `java8/README.md`, `java11/README.md`, etc.

## ❓ FAQ

**Q: Do I need all JDKs installed?**  
A: No! Only install JDKs for modules you're working on.

**Q: Can I use Java 17 to work on the java8 module?**  
A: Yes! Java 17 can compile Java 8 bytecode. Just set the module SDK correctly.

**Q: What about toolchains.xml?**  
A: Optional. The project works fine without it. Only use if you want strict version enforcement.

**Q: Why remove Lombok?**  
A: Lombok caused javac compatibility issues across multiple Java versions. Modules can add it individually if needed.

**Q: Can I build all modules at once?**  
A: Yes (`mvn clean package`), but not recommended unless you have all JDKs. Better to work on one module at a time.

## 🚀 Next Steps

1. Read **[docs/SETUP.md](docs/SETUP.md)** for detailed instructions
2. Pick a module to start with (java11 or java17 recommended)
3. Install the JDK for that version
4. Start coding and learning!

Happy learning! 🎉

