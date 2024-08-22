package br.com.omarcovelho;

import lombok.RequiredArgsConstructor;

import javax.sound.midi.ControllerEventListener;

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
}
