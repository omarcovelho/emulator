package br.com.omarcovelho.common;

public class DualBusRegister extends Register {

    private final Bus additionalBus;

    public DualBusRegister(Bus bus, Clock clock, String id, Bus additionalBus) {
        super(bus, clock, id);
        this.additionalBus = additionalBus;
    }

    @Override
    protected void doSet() {
        this.value = Byte.of(additionalBus.getValue());
    }
}
