package br.com.omarcovelho.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FourBitBus {
    private int data;
    private final String id;
    private final int size = 4;
    private final int maxValue = (int) Math.pow(2, size);


    public int getValue() {
        return data;
    }

    public void put(int data) {
        if(data <= maxValue) {
            this.data = data;
        } else {
            throw new IllegalArgumentException(
                    String.format("this bus has only [%s] bits and does not support [%s], max value is [%s]", size, data, maxValue));
        }
    }
}
