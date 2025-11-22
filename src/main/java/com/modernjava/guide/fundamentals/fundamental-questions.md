# Fundamental & Keywords

## Question 1 — Is Java Pass by Value or Pass by Reference?
**Answer:** Java is strictly pass-by-value.

- For primitives (int, boolean, etc.) the actual value is copied.
- For objects, the *reference value* (pointer) is copied — not the object itself. Both caller and callee get a copy of the reference that points to the same object.
    - Mutating the object via that reference (e.g., `order.setCost(20)`) affects the same object and is visible to the caller.
    - Reassigning the parameter to a new object (e.g., `order = new Order(...)`) only changes the local copy of the reference and does not affect the caller's reference.

Example:
```java
void modifyOrder(Order order) {
    order.setCost(20); // caller sees this change
}

void reassignTheObject(Order order) {
    order = new Order(...); // caller's reference unchanged
}
```

## Question 2 — Immutable class. How to create it further ensure its fields should not be modified?
**Answer:** Immutable class within Java is a class whose objects can't  be modified after they are created.

Rules for creating an Immutable class
1. Define class using final keyword
2. Make all its variables private and final, ensuring fields can be initialized within constructor and further can't be modified, not even within the class
3. Further don't define setter methods
4. Initialize all variables within the constructor.

Example:
```java
public Person(String name, int age, List<String> contacts) {
  this.name = name;
  this.age = age;
  this.contacts = contacts;
}
```
NOTE- 
1. with the above approach the contacts list can modified, since list is mutable field.
2. It is recommended to perform deep cloning of modifiable fields or create unmodifiable fields.

## Question 3- Design a fully immutable class. If the class contains a List<String>, and a getter provides access to this list, how can you prevent the list from being mutated by external code? (Suggest a solution other than Collections.immutableList).
**Answer:** To prevent List<String> from being modified we can try below options-
1. Within constructor create list using Collections.unmodifiableList by passing the list parameter.
2. Whenever getter method for list is called, always return a copy of original list to prevent modification.

Example:
```java
public PersonV2(String name, int age, List<String> contacts) {
  this.name = name;
  this.age = age;
  this.contacts = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(contacts)));
}
```