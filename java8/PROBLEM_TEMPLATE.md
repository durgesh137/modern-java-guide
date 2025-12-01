# Problem Template Guide

Use this template when adding new problems to the java8 module.

## File Structure for Each Problem

For a problem named `XYZ`, create three files:

1. `XYZTraditional.java` - Traditional pre-Java 8 solution
2. `XYZStream.java` - Java 8 Stream API solution
3. `XYZComparison.java` - Runner that compares both approaches

## Problem Ideas to Implement

### Easy
- [ ] Find max/min element
- [ ] Remove duplicates from list
- [ ] Count occurrences of element
- [ ] Convert list to uppercase strings
- [ ] Check if any/all elements match condition

### Medium
- [ ] Group strings by length
- [ ] Find top K frequent elements
- [ ] Flatten nested lists
- [ ] Partition list into even/odd
- [ ] Calculate average of filtered numbers

### Hard
- [ ] Find longest increasing subsequence length
- [ ] Custom grouping with multiple criteria
- [ ] Complex transformations with multiple maps
- [ ] Parallel processing optimization
- [ ] Custom collectors

## Best Practices

1. **Always handle null/empty inputs** gracefully
2. **Add complexity analysis** (time and space)
3. **Include multiple test cases** (normal, edge, large)
4. **Document why Stream API is better** (or when it's not)
5. **Show alternative Stream implementations** when instructive
6. **Compare performance** with realistic data sizes
7. **Add comments explaining each stream operation**
8. **Keep code simple and focused** on the concept being taught

## Running Your Problem

```bash
# Compile
mvn -pl java8 clean compile

# Run comparison
mvn -pl java8 exec:java -Dexec.mainClass="com.modernjava.guide.java8.Q001_print_numbers.PrintNumbersComparison"
```

