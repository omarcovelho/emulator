package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.BusSubscriber;

import java.util.ArrayList;
import java.util.List;

public class AbstractBus {
    private int data;
    private final String id;
    private final int size;
    private final int maxValue;

    private final List<BusSubscriber> watchers = new ArrayList<>();

    public AbstractBus(String id, int size) {
        this.id = id;
        this.size = size;
        this.maxValue = (int) Math.pow(2, size);
    }

    public int getValue() {
        return data;
    }

    public void put(int data) {
        if(data <= maxValue) {
            System.out.println("Data put on bus [" + this.id + "]: " + data);
            this.data = data;
            watchers.forEach(BusSubscriber::onBusChange);
        } else {
            throw new IllegalArgumentException(
                    String.format("this bus has only [%s] bits and does not support [%s], max value is [%s]", size, data, maxValue));
        }
    }
    public void subscribe(BusSubscriber busSubscriber) {
        this.watchers.add(busSubscriber);
    }
}