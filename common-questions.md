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

## 4.