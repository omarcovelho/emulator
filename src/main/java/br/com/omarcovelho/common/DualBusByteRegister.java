package br.com.omarcovelho.common;

public class DualBusByteRegister extends ByteRegister {

    private final ByteBus additionalBus;

    public DualBusByteRegister(ByteBus bus, Clock clock, String id, ByteBus additionalBus) {
        super(bus, clock, id);
        this.additionalBus = additionalBus;
    }

    @Override
    protected void doSet() {
        this.value = Byte.of(additionalBus.getValue());
    }
}
