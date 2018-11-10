package com.java;


public class Outer {
    private int value ;
    public boolean compare(Outer otherOuter){
        return this.value < otherOuter.value;
    }
}