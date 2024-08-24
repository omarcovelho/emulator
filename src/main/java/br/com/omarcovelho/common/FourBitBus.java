package br.com.omarcovelho.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FourBitBus extends AbstractBus {

    public FourBitBus(String id) {
        super(id, 4);
    }
}
