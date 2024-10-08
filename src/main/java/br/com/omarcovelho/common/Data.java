package br.com.omarcovelho.common;

import lombok.Getter;

@Getter
public abstract class Data {

    private final int value;
    private final int size;

    protected Data(int value, int size) {
        this.value = value;
        this.size = size;
        int maxValue = (int) Math.pow(2, size) - 1;
        if(value > maxValue) {
            throw new IllegalArgumentException(
                    String.format("this datatype has [%s] bits and does not support [%s]. max value is [%s]", size, value, maxValue));
        }
    }

    public int toInt(){
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("%" + size + "s", Integer.toBinaryString(value)).replace(' ', '0');
    }
}
