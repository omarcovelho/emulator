package br.com.omarcovelho.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FourBitBus extends AbstractBus {

    public FourBitBus(String id) {
        super(Nibble.of(0), id, 4);
    }
}
