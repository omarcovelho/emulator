package br.com.omarcovelho.common;

import org.apache.commons.lang.ArrayUtils;

public abstract class Data {

    private final int value;

    protected Data(int value, int size) {
        this.value = value;
        int maxValue = (int) Math.pow(2, size) - 1;
        if(value > maxValue) {
            throw new IllegalArgumentException(
                    String.format("this datatype has [%s] bits and does not support [%s]. max value is [%s]", size, value, maxValue));
        }
    }

    public int toInt(){
        return this.value;
    }
}
