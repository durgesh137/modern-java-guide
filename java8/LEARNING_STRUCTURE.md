# Java 8 Stream API Learning Structure

## Project Status (Last Updated: December 1, 2025)


### Quick Status
- ✅ **Core Examples** - FilterAndSum (Traditional + Stream + Comparison) - WORKING
- ✅ **Q001: Print Numbers** - Complete (Traditional + Stream + Comparison) - WORKING

### Action Required
Create `SumOfSquaresStream.java` to complete Q002 and enable its comparison runner.

---

## Overview

The java8 module has been set up with a comprehensive structure to learn Stream API by comparing it with traditional pre-Java 8 approaches. This allows you to understand:

- **Why** streams were introduced
- **When** to use streams vs traditional loops
- **How** to think functionally and transition from imperative to declarative style

## Package Structure (Current State)


## What's Been Created (Current Files)

### 1. Practice Problems (Current)

#### ✅ Q001: Print All Numbers in a List
**Location:** `problems/collections/Q001_print_numbers/`

**Problem:** Print all numbers in a list

**Input:** `[1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10]`

**Files:**
- ✅ `PrintNumbersTraditional.java` - Uses for-each loop
- ✅ `PrintNumbersStream.java` - Uses stream().forEach()
- ✅ `PrintNumbersComparison.java` - Full comparison with performance analysis

**Key Concepts:**
- Basic stream operations
- Method references (`System.out::println`)
- Lambda expressions
- forEach() terminal operation

---

### 3. Documentation Files

- ✅ `README.md` - Module overview and resources
- ✅ `LEARNING_STRUCTURE.md` - This file
- ✅ `PROBLEM_TEMPLATE.md` - Template for adding new problems

#### PROBLEM_TEMPLATE.md
A complete guide for adding new problems, including:
- File structure templates
- Code templates for Traditional/Stream/Comparison classes
- Best practices
- Problem ideas list (Easy/Medium/Hard)
- Running instructions

#### Updated README.md
- Project structure diagram
- Learning approach explanation
- Running examples
- Java 8 resources

## Running the Examples

### Compile the module
```bash
mvn -pl java8 clean compile
```

### ✅ Working Examples (Can Run Now)

#### 1. Q001: Print Numbers Comparison
```bash
mvn -pl java8 exec:java -Dexec.mainClass="com.modernjava.guide.java8.Q001_print_numbers.PrintNumbersComparison"
```

**Output shows:**
- Traditional for-each loop approach
- Stream API with method references
- Alternative stream variations (lambda, custom formatting)
- Performance comparison with JIT warmup
- Detailed pros/cons summary

---

## Learning Path (Based on Current Files)

### For Beginners (Start Here!)
1. ✅ **Q001: Print Numbers** - The simplest example (WORKING)
   - Read `PrintNumbersTraditional.java` - understand basic for-each loop
   - Read `PrintNumbersStream.java` - see your first stream operation
   - Run `PrintNumbersComparison.java` - see them side-by-side
3. Compare traditional vs stream code styles

## Problem Ideas to Implement Next

### ✅ Completed Problems
- [x] **Q001**: Print all numbers in a list (collections) - **FULLY WORKING**


### 📝 Easy Problems (Recommended Next)
- [ ] **Q003**: Print even numbers only
- [ ] **Q004**: Find sum of all numbers
- [ ] **Q005**: Find max/min element
- [ ] **Q006**: Count even vs odd numbers
- [ ] **Q007**: Remove duplicates from list
- [ ] **Q008**: Count occurrences of specific element
- [ ] **Q009**: Convert list to uppercase strings
- [ ] **Q010**: Check if any/all elements match condition

### 🔨 Medium Problems
- [ ] **Q011**: Find numbers divisible by 3 and 5
- [ ] **Q012**: Group strings by length
- [ ] **Q013**: Find top K frequent elements
- [ ] **Q014**: Flatten nested lists
- [ ] **Q015**: Partition list into even/odd
- [ ] **Q016**: Calculate average of filtered numbers
- [ ] **Q017**: Find first N Fibonacci numbers
- [ ] **Q018**: Merge and sort multiple lists
- [ ] **Q019**: Find second highest number
- [ ] **Q020**: Group by multiple criteria

### 🚀 Hard Problems
- [ ] **Q021**: Find longest increasing subsequence length
- [ ] **Q022**: Custom grouping with complex criteria
- [ ] **Q023**: Complex transformations with multiple maps
- [ ] **Q024**: Parallel processing optimization
- [ ] **Q025**: Custom collectors implementation
- [ ] **Q026**: Sliding window operations
- [ ] **Q027**: Real-time data processing simulation
- [ ] **Q028**: Complex nested object transformations
- [ ] **Q029**: Performance optimization challenges
- [ ] **Q030**: Stream debugging and error handling

## Key Benefits of This Structure

1. **Comparative Learning** - See both approaches side-by-side
2. **Practical Examples** - Real problems with real solutions
3. **Performance Insights** - Understand when streams are faster/slower
4. **Executable Code** - Run and experiment immediately
5. **Template-Driven** - Easy to add more problems
6. **Well-Documented** - Each class has detailed javadoc

## Next Steps

### To Continue Learning
1. **Add more problems** using `PROBLEM_TEMPLATE.md` as a guide
2. **Follow the numbering pattern** - Q00X_problem_name for easy tracking
3. **Always create 3 files per problem:**
   - `XxxTraditional.java` - Pre-Java 8 approach
   - `XxxStream.java` - Java 8 Stream API approach
   - `XxxComparison.java` - Side-by-side comparison with performance
4. **Solve problems from** LeetCode/HackerRank both ways
5. **Experiment** with different Stream operations:
   - `flatMap()` for nested structures
   - `groupingBy()` for complex aggregations
   - `partitioningBy()` for splitting data
   - Custom collectors

## Resources

- Java 8 Stream API Tutorial: https://docs.oracle.com/javase/tutorial/collections/streams/
- Stream API Javadoc: https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
- Problem Template: `java8/PROBLEM_TEMPLATE.md`
- Troubleshooting: `docs/TROUBLESHOOTING.md`

