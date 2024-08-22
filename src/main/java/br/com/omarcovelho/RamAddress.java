package br.com.omarcovelho;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RamAddress {
    private Byte value = new Byte();
    private final Bus bus;

    public void set() {
        value = bus.getValue();
    }

    public Byte getValue() {
        return Byte.of(value);
    }
}
