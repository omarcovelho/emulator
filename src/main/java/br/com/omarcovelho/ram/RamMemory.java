package br.com.omarcovelho.ram;

import br.com.omarcovelho.common.Bus;
import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ControlledComponent;

public class RamMemory extends ControlledComponent {
    private final Byte[] values = new Byte[256];
    private final Bus bus;

    public RamMemory(Bus bus) {
        this.bus = bus;
        this.values[0] = Byte.of(0b00100011);
        this.values[1] = Byte.of(0b10000000);
        this.values[2] = Byte.of(0b00100010);
        this.values[3] = Byte.of(0b11000000);
        this.values[4] = Byte.of(0b00100001);
        this.values[5] = Byte.of(0b11100000);
        this.values[6] = Byte.of(0b00100000);
        this.values[7] = Byte.of(0b11110000);
    }

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
