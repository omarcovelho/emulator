package br.com.omarcovelho.ram;

import br.com.omarcovelho.common.Bus;
import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ControlledComponent;

public class RamMemory extends ControlledComponent {
    private final Byte[] values = new Byte[256];
    private final Bus bus;

    public RamMemory(Bus bus) {
        this.bus = bus;
        this.values[0] = Byte.of(0b00100000);//load data to r0
        this.values[1] = Byte.of(0b00010000);//data loaded to r0 address to read from
        this.values[2] = Byte.of(0b00100001);//load data to r1
        this.values[3] = Byte.of(0b11111111);//data loaded to r1 address to write to

        this.values[4] = Byte.of(0b00010001);//store to r1 what is in r0 address
        this.values[5] = Byte.of(0b00000010);//load from r0 address on r2
//        this.values[6] = Byte.of(0b00100000);
//        this.values[7] = Byte.of(0b11110000);

        this.values[16] = Byte.of(0b00000000);//should be stored on r1
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
