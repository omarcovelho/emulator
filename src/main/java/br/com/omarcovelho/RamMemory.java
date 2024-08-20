package br.com.omarcovelho;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RamMemory {
    private final Byte[] values = new Byte[256];
    private final Bus bus;

    public void set(RamAddress address) {
        values[address.getValue().toInt()] = Byte.of(bus.getValue());
    }

    public void enable(RamAddress address) {
        this.bus.put(getRamValue(address));
    }

    private Byte getRamValue(RamAddress address) {
        return Byte.of(values[address.getValue().toInt()]);
    }
}
