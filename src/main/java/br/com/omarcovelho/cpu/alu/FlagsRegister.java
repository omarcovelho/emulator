package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.common.Clockable;
import br.com.omarcovelho.common.FourBitBus;
import br.com.omarcovelho.common.NibbleRegister;
import lombok.Getter;
import lombok.Setter;

@Getter
public class FlagsRegister extends NibbleRegister implements Clockable {
    @Setter
    private boolean carryOut;
    @Setter
    private boolean aLarger;
    @Setter
    private boolean equal;
    @Setter
    private boolean zero;

    public FlagsRegister(FourBitBus flagsBus, Clock clock) {
        super(flagsBus, "flagsRegister", clock);
        this.subscribe(clock);
    }

    @Override
    public void clkSet() {
        if(this.isSet()) {
            doSet();
        }
    }

    protected void doSet() {
        this.carryOut = (getBus().getValue() & 0b1000) > 0;
        this.aLarger = (getBus().getValue() & 0b0100) > 0;
        this.equal = (getBus().getValue() & 0b0010) > 0;
        this.zero = (getBus().getValue() & 0b0001) > 0;
    }
}
