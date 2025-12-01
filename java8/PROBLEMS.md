# Java 8 Problems & Concepts

## Completed Problems

### ✅ Q001: Print All Numbers
**Location:** `java8/Q001_print_numbers/`  
**Concepts:** Stream basics, forEach(), method references, lambda expressions  
**Problem:** Print all numbers in a list  
**Input:** `[1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10]`  
**Traditional:** for-each loop  
**Stream:** `numbers.stream().forEach(System.out::println)`

---

## Partially Completed

### ⚠️ Q002: Print Even numbers
**Location:** `java8/Q002_print_evens/`  
**Concepts:** filter(), forEach(), method references, lambda expressions  
**Problem:** Print all even numbers present within a list  
**Input:** `[1, 2, 3, 4, 5]` → **Output:** `2,4`  
**Traditional:** ✅ `PrintEvensTraditional.java`  
**Stream:** ✅ `PrintEvensStream.java`  
**Comparison:** ✅ `PrintEvensComparison.java`

---

## Core Concepts Covered

### Stream Operations
- **Terminal Operations:** `forEach()`, `count()`, `sum()`
- **Intermediate Operations:** `filter()`, `map()`
- **Method References:** `System.out::println`, `Integer::intValue`
- **Lambda Expressions:** `n -> n % 2 == 0`, `n -> n * n`

### Comparison Topics
- Traditional vs Stream syntax
- Performance analysis with JIT warmup
- Code readability and maintainability
- When to use each approach

---

## Planned Problems

### Easy
- Q003: Print even numbers only
- Q004: Find sum of all numbers
- Q005: Find max/min element
- Q006: Count even vs odd numbers
- Q007: Remove duplicates

### Medium
- Q011: Group strings by length
- Q012: Find top K frequent elements
- Q013: Flatten nested lists
- Q014: Partition list into even/odd

### Hard
- Q021: Custom collectors
- Q022: Parallel processing optimization
- Q023: Complex nested transformations

