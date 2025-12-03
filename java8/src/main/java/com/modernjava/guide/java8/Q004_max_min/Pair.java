package com.modernjava.guide.java8.Q004_max_min;


public class Pair {
    private int minElement;
    private int maxElement;

    public Pair(int minElement, int maxElement) {
        this.minElement = minElement;
        this.maxElement = maxElement;
    }

    public int getMinElement() {
        return minElement;
    }

    public void setMinElement(int minElement) {
        this.minElement = minElement;
    }

    public int getMaxElement() {
        return maxElement;
    }

    public void setMaxElement(int maxElement) {
        this.maxElement = maxElement;
    }

}
