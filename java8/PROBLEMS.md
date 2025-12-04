# Java 8 Problems & Concepts

## 📂 Module Structure

```
java8/
├── pom.xml                          # Module build configuration
├── README.md                        # Module overview and quick start
├── PROBLEMS.md                      # This file - problem statements and status
├── LEARNING_STRUCTURE.md            # Complete learning guide
├── PROBLEM_TEMPLATE.md              # Template for adding new problems
├── Troubleshoot.md                  # Common errors and solutions
├── src/
│   └── main/
│       └── java/
│           └── com/modernjava/guide/
│               ├── Main.java                                    # Module entry point
│               └── java8/
│                   ├── Q001_print_numbers/
│                   │   ├── PrintNumbersComparison.java          # ✅ Complete
│                   │   ├── PrintNumbersStream.java              # ✅ Complete
│                   │   └── PrintNumbersTraditional.java         # ✅ Complete
│                   ├── Q002_print_evens/
│                   │   ├── PrintEvensComparison.java            # ✅ Complete
│                   │   ├── PrintEvensStream.java                # ✅ Complete
│                   │   └── PrintEvensTraditional.java           # ✅ Complete
│                   ├── Q003_square_nums/
│                   │   ├── SumOfSquareOfNumberComparison.java   # ✅ Complete
│                   │   ├── SumOfSquareOfNumberStream.java       # ✅ Complete
│                   │   └── SumOfSquareOfNumberTraditional.java  # ✅ Complete
│                   ├── Q004_max_min/
│                   │   ├── MaxMinNumberComparison.java          # ✅ Complete
│                   │   ├── MaxMinNumberStream.java              # ✅ Complete
│                   │   ├── MaxMinNumberTraditional.java         # ✅ Complete
│                   │   └── Pair.java                            # ✅ Helper class
│                   ├── Q005_print_string/
│                   │   ├── PrintStringsComparison.java          # ✅ Complete
│                   │   ├── PrintStringsStream.java              # ✅ Complete
│                   │   └── PrintStringsTraditional.java         # ✅ Complete
│                   ├── Q006_count_evens_odds/
│                   │   └── CountEvensOddsComparison.java        # ✅ Complete (Single class)
│                   └── util/
│                       └── ComparisonUtils.java                 # ✅ Shared utilities
└── target/                          # Compiled output (not tracked in git)
```

---

## ✅ Completed Problems

### Q001: Print All Numbers
**Location:** `com.modernjava.guide.java8.Q001_print_numbers`  
**Concepts:** Stream basics, forEach(), method references, lambda expressions  
**Problem:** Print all numbers in an array  
**Input:** `[1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10]`  
**Traditional:** for-each loop  
**Stream:** `Arrays.stream(numbers).forEach(System.out::println)`  
**Comparison:** ✅ `PrintNumbersComparison.java`

---

### Q002: Print Even Numbers
**Location:** `com.modernjava.guide.java8.Q002_print_evens`  
**Concepts:** filter(), forEach(), predicates, lambda expressions  
**Problem:** Print all even numbers present within an array  
**Input:** `[1, 2, 3, 4, 5]` → **Output:** `2, 4`  
**Traditional:** ✅ `PrintEvensTraditional.java` - for loop with modulo check  
**Stream:** ✅ `PrintEvensStream.java` - `filter(n -> n % 2 == 0)`  
**Comparison:** ✅ `PrintEvensComparison.java`

---

### Q003: Sum of Square of Numbers
**Location:** `com.modernjava.guide.java8.Q003_square_nums`  
**Concepts:** map(), reduce(), sum(), transformations  
**Problem:** Calculate sum of squares of all numbers in an array  
**Input:** `[1, 2, 3, 4, 5]` → **Output:** `55` (1² + 2² + 3² + 4² + 5²)  
**Traditional:** ✅ `SumOfSquareOfNumberTraditional.java` - for loop with accumulator  
**Stream:** ✅ `SumOfSquareOfNumberStream.java` - `map(n -> n * n).sum()`  
**Comparison:** ✅ `SumOfSquareOfNumberComparison.java` - includes performance benchmarks

---

### Q004: Find Max and Min Numbers
**Location:** `com.modernjava.guide.java8.Q004_max_min`  
**Concepts:** max(), min(), Comparator, Optional handling  
**Problem:** Find both maximum and minimum values in an array  
**Input:** `[45, 22, 89, 11, 67, 34, 90, 5, -121, 342, 0, 9999, -5000]`  
**Output:** `Max = 9999, Min = -5000`  
**Traditional:** ✅ `MaxMinNumberTraditional.java` - single pass with variables  
**Stream:** ✅ `MaxMinNumberStream.java` - `max()` and `min()` terminal operations  
**Comparison:** ✅ `MaxMinNumberComparison.java` - multiple test cases with varying sizes  
**Helper:** ✅ `Pair.java` - simple class to hold max/min result

---

### Q005: Filter Strings by Substring
**Location:** `com.modernjava.guide.java8.Q005_print_string`  
**Concepts:** filter(), contains(), String operations, forEach()  
**Problem:** Print all strings containing a specific substring (e.g., "_test")  
**Input:** `["Apple_test", "Banana", "Cherry_test", "Date", "Elderberry", "Fig_test"]`  
**Output:** `Apple_test, Cherry_test, Fig_test`  
**Traditional:** ✅ `PrintStringsTraditional.java` - for loop with contains() check  
**Stream:** ✅ `PrintStringsStream.java` - `filter(n -> n.contains(substring))`  
**Comparison:** ✅ `PrintStringsComparison.java` - tests with 20, 1K, 100K, 1M strings  
**Note:** Large array outputs are suppressed for performance testing

---

### Q006: Count Even and Odd Numbers
**Location:** `com.modernjava.guide.java8.Q006_count_evens_odds`  
**Concepts:** count(), filter(), predicates, single-pass iteration  
**Problem:** Count how many even and odd numbers are in an array  
**Input:** `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`  
**Output:** `Even count: 5, Odd count: 5`  
**Implementation:** ✅ `CountEvensOddsComparison.java` - **Single class with both approaches**  
**Methods:**
  - `countTraditional()` - Single for loop counting both even/odd
  - `countStream()` - Two separate stream pipelines with filter().count()
**Test Cases:** 10, 1K, 100K elements  
**Note:** This demonstrates the **single-class approach** for simple problems - all code in one file

---

## 🛠️ Utility Classes

### ComparisonUtils
**Location:** `com.modernjava.guide.java8.util.ComparisonUtils`  
**Purpose:** Shared helper methods for all comparison classes

**Methods:**
- `repeat(String str, int count)` - Repeats string N times (Java 8 compatible alternative to String.repeat())
- `formatTime(long nanos)` - Formats nanoseconds to human-readable format (ns, μs, ms)
- `printNumbers(int[] numbers)` - Prints integer array elements comma-separated
- `printStrings(String[] strings)` - Prints string array elements comma-separated
- `getArrayOfSpecifiedSize(int size)` - Generates random int array with positive, negative, and zero values (range: -100 to +100)
- `getStringArrayWithPatternAtEnd(int size, String pattern)` - Generates random string array from sample pool, randomly appending pattern to some strings
- `getStringArray()` - Returns the predefined string samples array

---

## 🎯 Core Concepts Covered

### Stream Operations
- **Terminal Operations:** `forEach()`, `count()`, `sum()`, `max()`, `min()`
- **Intermediate Operations:** `filter()`, `map()`
- **Method References:** `System.out::println`, `Integer::compare`
- **Lambda Expressions:** `n -> n % 2 == 0`, `n -> n * n`, `n -> n.contains(substring)`
- **String Operations:** `contains()`, filtering by substring

### Comparison Topics
- Traditional vs Stream syntax
- Performance analysis with JIT warmup
- Code readability and maintainability
- When to use each approach
- Handling edge cases (empty arrays, single elements)
- Output suppression for large datasets in performance testing
- **Single-class approach** for simple problems (Q006+)

### Java 8 Features Demonstrated
- ✅ Lambda expressions
- ✅ Method references
- ✅ Stream API basics (int and object streams)
- ✅ Functional interfaces
- ✅ Optional (in max/min operations)
- ✅ String filtering and predicates
- ✅ Counting with filter().count()

---

## 📊 Performance Notes

Based on benchmark results in comparison classes:
- **Small arrays (< 100 elements):** Traditional approach is faster due to Stream overhead
- **Large arrays (10,000+ elements):** Stream performance improves, gap narrows
- **Very large arrays (1,000,000+ elements):** Traditional still edges out for simple operations
- **String operations:** Similar patterns - traditional loops excel for simple contains() checks
- **Counting operations:** Traditional single-pass is more efficient than multiple stream pipelines
- **Takeaway:** Use Streams for readability and composition, not raw speed in tight loops

---

## 🚧 Planned Problems

### Easy
- Q007: Remove duplicates from array
- Q008: Convert all strings to uppercase
- Q009: Find average of numbers
- Q010: Check if any element matches a condition
- Q011: Sum all positive numbers

### Medium
- Q011: Group strings by length
- Q012: Find top K frequent elements
- Q013: Flatten nested lists
- Q014: Partition numbers into even/odd lists
- Q015: Find first element matching condition
- Q014: Partition list into even/odd

### Hard
- Q021: Custom collectors
- Q022: Parallel processing optimization
- Q023: Complex nested transformations

