package com.modernjava.guide.fundamentals.q1;


public class Order {
    private int cost;
    private String name;

    public Order(int cost, String name){
        this.cost = cost;
        this.name = name;
    }

    public void setCost(int newCost){
        this.cost = newCost;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
