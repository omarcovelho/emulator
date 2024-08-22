package br.com.omarcovelho;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Alu {
//    private final Register a;
    private final Register tmp;
    private final Register acc;
    @Setter
    private boolean carryIn;
    @Setter
    private boolean carryOut;
    private boolean aLarger;
    private boolean equal;
    private boolean zero;
    private final Bus accBus;
    private final Bus commonBus;

    public Alu(Clock clock, Bus bus) {

//        this.a = new Register(bus, clock);
        this.tmp = new Register(bus, clock);
        this.accBus = new Bus();
        this.acc = new Register(accBus,clock);
        this.commonBus = bus;
    }

    public void add() {
        int sum = commonBus.getValue().toInt() + tmp.getValue().toInt() + (carryIn ? 1 : 0);
        accBus.put(Byte.of(sum & 0b11111111));
        carryOut = (sum & 0b100000000) > 0;
    }

    public void shiftRight() {
        int value = commonBus.getValue().toInt();
        carryOut = (value & 0b00000001) > 0;
        accBus.put(Byte.of(value >> 1 | (carryIn ? 0b10000000 : 0)));
    }

    public void shiftLeft() {
        int value = commonBus.getValue().toInt();
        carryOut = (value & 0b10000000) > 0;
        int binResult = commonBus.getValue().toInt() << 1 | (carryIn ? 0b00000001 : 0);
        accBus.put(Byte.of(binResult & 0b11111111));
    }

    public void not() {
        accBus.put(Byte.of(~commonBus.getValue().toInt()));
    }

    public void and() {
        accBus.put(Byte.of(commonBus.getValue().toInt() & tmp.getValue().toInt()));
    }


    public void or() {
        accBus.put(Byte.of(commonBus.getValue().toInt() | tmp.getValue().toInt()));
    }

    public void xor() {
        accBus.put(Byte.of(commonBus.getValue().toInt() ^ tmp.getValue().toInt()));
    }

    public void compare() {
        accBus.put(Byte.of(commonBus.getValue().toInt() ^ tmp.getValue().toInt()));
        aLarger = commonBus.getValue().toInt() > tmp.getValue().toInt();
        equal  = commonBus.getValue().equals(tmp.getValue());
    }
}
