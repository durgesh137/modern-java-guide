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
