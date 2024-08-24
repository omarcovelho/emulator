package br.com.omarcovelho.ram;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ByteBus;
import br.com.omarcovelho.common.ControlledComponent;

public class RamMemory extends ControlledComponent {
    private final Byte[] values = new Byte[256];
    private final ByteBus bus;

    public RamMemory(ByteBus bus, Byte[] program) {
        this.bus = bus;
        for(int i = 0; i < values.length; i++) {
            values[i] = program[i] != null ? program[i] : new Byte();
        }
    }

    public void set(RamAddress address) {
        values[address.getValue().toInt()] = Byte.of(bus.getValue());
    }

    public void enable(RamAddress address) {
        this.bus.put(getRamValue(address).toInt());
    }

    private Byte getRamValue(RamAddress address) {
        return Byte.of(values[address.getValue().toInt()]);
    }
}
