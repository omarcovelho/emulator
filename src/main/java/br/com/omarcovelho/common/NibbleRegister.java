package br.com.omarcovelho.common;

public class NibbleRegister extends AbstractRegister {

    public NibbleRegister(AbstractBus bus, String id, Clock clock) {
        super(Nibble.of(0), bus, id, clock);
    }
}
