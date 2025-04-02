# Common-questions

## 1. How to handle null pointer with lambdas
### 1.1 handling null value for string
Approach 1, with Optional in Java 8
```java
    Function<String, Integer> getLength = s ->
    Optional.ofNullable(s).map(String::length).orElse(0);
```
Approach 2, with ternary operator
```java
    Function<String, Integer> getLength = (String s) -> s != null? s.length():0;
    System.out.println(getLength.apply(null));
```
## 2 when to use lambdas vs normal methods

### 2.1 Key Decision Table

| Scenario                          | Lambda | Normal Method |
|-----------------------------------|--------|---------------|
| **Short transformations**<br>(e.g., `s -> s.length()`) | ✅ Yes | ❌ No |
| **Complex logic**<br>(branches, loops, exceptions) | ❌ No | ✅ Yes |
| **Reusable logic**               | ❌ No | ✅ Yes |
| **Functional APIs**<br>(Streams, `Optional`, etc.) | ✅ Yes | ❌ No |
| **Debugging needs**              | ❌ No | ✅ Yes |

### 2.2 Usage Recommendations
#### When to Prefer Lambdas
- Simple one-line transformations
- Functional programming contexts (Streams, Optional)
- Callback implementations
- Inline behavior configuration

#### When to Prefer Normal Methods
- Multi-step business logic
- Operations requiring proper exception handling
- Code that needs reuse across classes
- Cases where stack trace readability matters

#### Example

```java
// Good lambda use (simple transformation)
List<String> names = Arrays.asList("Alice", "Bob");
List<Integer> lengths = names.stream()
                           .map(String::length)  // ✅ Lambda
                           .collect(Collectors.toList());

// Better as normal method (complex logic)
public static boolean isValidUser(User user) {  // ✅ Method
    if (user == null) return false;
    if (user.getName() == null) return false;
    return user.getAge() >= 18 && user.getAge() <= 120;
}
```
## 3. Why effective final concept did not occur in below code
```java
        Predicate<Integer> isEven = ( x) -> x%2==0;
        int num = 12;
        boolean test = isEven.test(num);
        System.out.println("Is "+num+" even? "+test);
        num=11;
        test = isEven.test(num);
        System.out.println("Is "+num+" even? "+test);
```
## 3.1 Explanation:
- Since num is just passed as an argument to test(), not captured by the lambda.
- If the lambda referenced num (rather than just taking it as a parameter), then num would need to be effectively final.

## 4. Stream Api operations
Java 8 Stream api provides declaration way to process collections.
It is divided into 3 main operations, namely-
1. Source(e.g., List.stream(), Arrays.stream())
2. Intermediate Operations(e.g., filter, map, sorted, distinct)
3. Terminal Operations(e.g., collect, reduce, forEach, findFirst)

### 4.1 Stream Pipeline structure
```java
List<Result> result = collection.stream()  // Source
    .filter(x -> condition)              // Intermediate
    .map(x -> transform)                 // Intermediate
    .collect(Collectors.toList());       // Terminal
```

### 4.2 intermediate operations
#### 4.2.1 filter(Predicate)	
It Keeps elements matching a condition
Format: .filter(n -> n % 2 == 0)

#### 4.2.2 map(Function)	
Transforms each element
Format: .map(String::toUpperCase)

#### 4.3 sorted()
- Sorts elements
Format: .sorted(Comparator.reverseOrder())

#### 4.4 distinct()
Removes duplicates
Format: .distinct()

#### 4.5 limit(n)
Takes first n elements
Format: .limit(5)

#### 4.6 skip(n)
Skips first n elements
Format: .skip(2)

### 4.3 Terminal operations
#### 4.3.1 reduce operation
The reduce operation in Java 8 combines elements of a stream into a single result. 
It's a terminal operation, meaning it consumes the stream and produces a final value

1. reduce(T identity, BinaryOperator<T> accumulator):
- This form takes an identity value and a binary operator. 
- The identity value is the initial value for the reduction and is also returned if the stream is empty. 
- This form returns a T.
- Example-
```java
        List<Integer> nums = Arrays.asList(null);
        // issue with below code, if only null value is present then, it will return 1
        Integer product = nums.stream().filter(Objects::nonNull).reduce(1, (a, b) -> a * b);
        System.out.println("Final Product: "+product);
```

2. reduce(BinaryOperator<T> accumulator):
- This form takes a binary operator as an argument, which is a function that combines two elements of the stream into one. 
- It returns an Optional<T> to handle cases where the stream is empty
- Example-
```java
        List<Integer> nums = Arrays.asList(1,5,10,1, 0, null);
        Optional<Integer> reduce = nums.stream().filter(Objects::nonNull)
                .filter(n -> n != 0)
                .reduce((a, b) -> a * b);

```
3. reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner):
- This form is mostly used with parallel streams.
- Three arguments include identity, accumulator, and combiner.
- Accumulator combines accumulated value with stream element, and combiner merges result of multiple accumulators

#### 4.3.2 

## 5. Function interface format
Function<T,R>
- Here T is the type which arguments will be of defined function
- R is the return type that we will get from function, 
Example-
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);  // Single abstract method
}
```

## 6. BiFunction
The BiFunction<T, U, R> is a functional interface introduced in Java 8 that represents a function that:
- Accepts two input arguments of types T and U
- Returns a result of type R

Definition-
```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);  // Single abstract method
}
```
### 6.1 Adding numbers
```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(5, 3));  // 8
```