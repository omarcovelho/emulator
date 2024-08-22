package br.com.omarcovelho.ram;

import br.com.omarcovelho.common.Bus;
import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ControlledComponent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RamAddress extends ControlledComponent {
    private Byte value = new Byte();
    private final Bus bus;

    public void set() {
        value = bus.getValue();
    }

    public Byte getValue() {
        return Byte.of(value);
    }

    @Override
    public String toString() {
        return Integer.toBinaryString(value.toInt());
    }
}
