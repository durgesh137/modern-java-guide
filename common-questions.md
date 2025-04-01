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
### 1.2