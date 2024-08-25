package br.com.omarcovelho.common;

import lombok.Getter;
import lombok.Setter;

public class Bus1Register extends ByteRegister {

    @Getter
    @Setter
    private boolean isBypass;

    public Bus1Register(ByteBus bus, Clock clock, String id) {
        super(bus, clock, id);
    }

    @Override
    public Data getValue() {
        if(isBypass) {
            return Byte.of(1);
        } else {
            return this.value;
        }
    }
}
