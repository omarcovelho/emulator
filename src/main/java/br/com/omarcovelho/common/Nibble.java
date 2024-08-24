package br.com.omarcovelho.common;

public class Nibble extends Data {
    private Nibble(int value) {
        super(value, 4);
    }

    public static Nibble of(int value) {
        return new Nibble(value);
    }

    public static Nibble of(Byte value) {
        return new Nibble(value.toInt());
    }
}
