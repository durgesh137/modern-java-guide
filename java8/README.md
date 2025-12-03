# Module: java8

## 📚 Documentation

- **[PROBLEMS.md](PROBLEMS.md)** - Problem statements, concepts covered, and current status
- **[LEARNING_STRUCTURE.md](LEARNING_STRUCTURE.md)** - Complete learning guide and project structure
- **[PROBLEM_TEMPLATE.md](PROBLEM_TEMPLATE.md)** - Template for adding new problems
- **[Troubleshoot.md](Troubleshoot.md)** - Common errors and solutions

---

## 📂 Project Structure

```
java8/
├── pom.xml                          # Module build configuration (Java 8)
├── README.md                        # This file
├── PROBLEMS.md                      # Problem statements and status
├── LEARNING_STRUCTURE.md            # Learning guide
├── PROBLEM_TEMPLATE.md              # Template for new problems
├── Troubleshoot.md                  # Error solutions
├── src/
│   └── main/
│       └── java/
│           └── com/modernjava/guide/
│               ├── Main.java        # Module entry point
│               └── java8/
│                   ├── Q001_print_numbers/          # ✅ Print all numbers
│                   ├── Q002_print_evens/            # ✅ Filter even numbers
│                   ├── Q003_square_nums/            # ✅ Sum of squares
│                   ├── Q004_max_min/                # ✅ Find max & min
│                   └── util/
│                       └── ComparisonUtils.java     # ✅ Shared utilities
└── target/                          # Compiled output (gitignored)
```

---

## 🎯 Quick Start

### Prerequisites
- **Java 8 JDK** must be installed
- Maven 3.6+ (or use included `mvnw` wrapper)

### Build & Run

```bash
# Build only this module
mvn -pl java8 clean compile

# Build with tests
mvn -pl java8 clean package

# Run a specific problem comparison (example: Q001)
mvn -pl java8 exec:java -Dexec.mainClass="com.modernjava.guide.java8.Q001_print_numbers.PrintNumbersComparison"

# Run Q002: Print Evens
mvn -pl java8 exec:java -Dexec.mainClass="com.modernjava.guide.java8.Q002_print_evens.PrintEvensComparison"

# Run Q003: Sum of Squares
mvn -pl java8 exec:java -Dexec.mainClass="com.modernjava.guide.java8.Q003_square_nums.SumOfSquareOfNumberComparison"

# Run Q004: Max & Min
mvn -pl java8 exec:java -Dexec.mainClass="com.modernjava.guide.java8.Q004_max_min.MaxMinNumberComparison"
```

---

## 🔧 IDE Setup

### IntelliJ IDEA

1. **Download Java 8 JDK:**
   - `File | Project Structure...` (⌘;)
   - `Platform Settings → SDKs → +` → `Download JDK...`
   - Select `Temurin 8` or `Corretto 8`, download

2. **Set Module SDK:**
   - `File | Project Structure... → Modules`
   - Select `java8` module → `Module SDK` → choose Java 8

3. **Run Configuration:**
   - `Run | Edit Configurations...`
   - Create new `Application` configuration
   - Main class: e.g., `com.modernjava.guide.java8.Q001_print_numbers.PrintNumbersComparison`
   - JRE: Select Java 8

4. **Refresh Maven:**
   - Open Maven tool window (`View → Tool Windows → Maven`)
   - Click refresh button (↻)
   - If stale: `File → Invalidate Caches / Restart`

### VS Code
```bash
# Install Maven extension
# Command Palette (⌘⇧P) → "Maven: Update All Projects"
```

### Eclipse / STS
```bash
# Right-click project → Maven → Update Project...
# Check "Force Update of Snapshots/Releases"
```

---

## 📊 Current Status

### Completed Problems (4/4)
- ✅ **Q001:** Print All Numbers - Stream basics, forEach(), method references
- ✅ **Q002:** Print Even Numbers - filter(), predicates
- ✅ **Q003:** Sum of Squares - map(), sum(), transformations
- ✅ **Q004:** Max & Min - max(), min(), Optional handling

### Utility Classes
- ✅ **ComparisonUtils** - Shared helpers (repeat, formatTime, printNumbers, getArrayOfSpecifiedSize)

See **[PROBLEMS.md](PROBLEMS.md)** for detailed problem statements and concepts covered.

---

## 🎓 Learning Approach

Each problem demonstrates:
1. **Traditional Approach** - Classic Java loops and conditionals
2. **Stream Approach** - Java 8 Stream API with lambdas
3. **Comparison** - Side-by-side execution with performance metrics

### Key Concepts Covered
- Lambda expressions: `n -> n % 2 == 0`
- Method references: `System.out::println`
- Stream operations: `filter()`, `map()`, `forEach()`, `sum()`, `max()`, `min()`
- Performance analysis: nano-time benchmarks with multiple test cases
- Edge cases: empty arrays, single elements, large datasets

---

## ⚠️ Troubleshooting

### Common Error: `invalid flag: --release`
**Cause:** Maven compiler plugin using `--release` flag not supported by Java 8 toolchain

**Solution:**
```xml
<!-- In pom.xml, ensure <release> is NOT set for Java 8 -->
<plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <!-- Do NOT use <release>8</release> for Java 8 -->
    </configuration>
</plugin>
```

### Common Error: `ExceptionInInitializerError`
**Cause:** Mismatch between compile and runtime JDK versions

**Solution:**
- Ensure both Maven and IntelliJ use the same Java 8 JDK
- Check: `mvn -version` should show Java 8
- IntelliJ: Verify module SDK and run configuration JRE are both Java 8

See **[Troubleshoot.md](Troubleshoot.md)** for complete error reference.

---

## 🚀 Next Steps

1. Run existing comparison classes to see traditional vs stream approaches
2. Experiment with different input sizes to observe performance characteristics
3. Add new problems using `PROBLEM_TEMPLATE.md`
4. Explore more Stream API operations (collect, flatMap, distinct, sorted, etc.)

---

## 📝 Notes

- This module uses `<source>1.8</source>` and `<target>1.8</target>` configuration
- All utility methods are Java 8 compatible (no String.repeat(), no var, etc.)
- Performance benchmarks include JIT warmup considerations
- Random array generation uses range -100 to +100 (positive, negative, zero values)

---

**See also:**
- [Root README](../README.md) - Multi-module project overview
- [Git Best Practices](../docs/GIT_BEST_PRACTICES.md) - Contribution guidelines
- [Troubleshooting](../docs/TROUBLESHOOTING.md) - Global error reference
1. **Traditional Solution** - Pre-Java 8 imperative style with loops
2. **Stream Solution** - Java 8+ functional style with Stream API
3. **Comparison Runner** - Side-by-side execution with performance metrics

Example problems:
- **Q001: Print Numbers** - forEach(), method references, lambda expressions (WORKING)
- **Q002: Sum of Squares** - filter(), map(), reduce() (INCOMPLETE - needs Stream version)

**For detailed structure:** See [LEARNING_STRUCTURE.md](LEARNING_STRUCTURE.md)

---

## Java 8 Features & Resources

Features newly included within Java 8: https://www.oracle.com/java/technologies/javase/8-whats-new.html

Key topics to explore:
- **Stream API**: https://docs.oracle.com/javase/tutorial/collections/streams/
- **Lambda Expressions**: Functional interfaces and method references
- **Optional**: Better null handling
- **Date/Time API**: Modern date and time handling
- **Default Methods**: Interface evolution

Additional resources:
- Java learning path: https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
- Java 8 documentation: https://docs.oracle.com/javase/8/index.html

**Having issues?** Check [Troubleshoot.md](Troubleshoot.md) for common errors and solutions.
- GC tuning: https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/

Notes:
- If you use the `release` compiler flag (the default in this repo), make sure Maven runs with a JDK version that supports that `--release` value or configure toolchains to supply the correct JDK to the compiler plugin.
- See `docs/TOOLCHAINS.md` for more details.
