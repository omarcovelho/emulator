package br.com.omarcovelho;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Alu {
    private final Register a;
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

    public void add() {
        int sum = a.getValue().toInt() + tmp.getValue().toInt() + (carryIn ? 1 : 0);
        accBus.put(Byte.of(sum & 0b11111111));
        carryOut = (sum & 0b100000000) > 0;
    }

    public void shiftRight() {
        int value = a.getValue().toInt();
        carryOut = (value & 0b00000001) > 0;
        accBus.put(Byte.of(value >> 1 | (carryIn ? 0b10000000 : 0)));
    }

    public void shiftLeft() {
        int value = a.getValue().toInt();
        carryOut = (value & 0b10000000) > 0;
        int binResult = a.getValue().toInt() << 1 | (carryIn ? 0b00000001 : 0);
        accBus.put(Byte.of(binResult & 0b11111111));
    }

    public void not() {
        accBus.put(Byte.of(~a.getValue().toInt()));
    }

    public void and() {
        accBus.put(Byte.of(a.getValue().toInt() & tmp.getValue().toInt()));
    }


    public void or() {
        accBus.put(Byte.of(a.getValue().toInt() | tmp.getValue().toInt()));
    }

    public void xor() {
        accBus.put(Byte.of(a.getValue().toInt() ^ tmp.getValue().toInt()));
    }

    public void compare() {
        accBus.put(Byte.of(a.getValue().toInt() ^ tmp.getValue().toInt()));
        aLarger = a.getValue().toInt() > tmp.getValue().toInt();
        equal  = a.getValue().equals(tmp.getValue());
    }
}
