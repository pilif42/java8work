package com.philippe.app.util;

/**
 * How to use it:
 *      SingletonExample singleton = SingletonExample.INSTANCE;
 *
 * Origin of this example: https://dzone.com/articles/java-singletons-using-enum
 */
public enum SingletonExample {
    INSTANCE;

    int value;

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
