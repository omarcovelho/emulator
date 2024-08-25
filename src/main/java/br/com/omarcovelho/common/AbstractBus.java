package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.BusSubscriber;

import java.util.ArrayList;
import java.util.List;

public class AbstractBus {
    private Data data;
    private final String id;
    private final int size;
    private final int maxValue;
    private final List<BusSubscriber> watchers = new ArrayList<>();

    protected AbstractBus(Data initialData, String id, int size) {
        this.data = initialData;
        this.id = id;
        this.size = size;
        this.maxValue = (int) Math.pow(2, size) - 1;
    }

    public Data getValue() {
        return data;
    }

    public void put(Data data) {
        if(data.getSize() == this.size) {
            System.out.println("Data put on bus [" + this.id + "]: " + data);
            this.data = data;
            watchers.forEach(BusSubscriber::onBusChange);
        } else {
            throw new IllegalArgumentException(
                    String.format("this bus has only [%s] bits and does not support [%s], max value is [%s]", size, data, padInteger(maxValue)));
        }
    }

    private String padInteger(int value) {
        return String.format("%" + size + "s", Integer.toBinaryString(value)).replace(' ', '0');
    }

    public void subscribe(BusSubscriber busSubscriber) {
        this.watchers.add(busSubscriber);
    }
}
