package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.common.Clockable;
import br.com.omarcovelho.common.ControlledComponent;
import br.com.omarcovelho.common.FourBitBus;
import lombok.Getter;
import lombok.Setter;

@Getter
public class FlagsRegister extends ControlledComponent implements Clockable {
    @Setter
    private boolean carryOut;
    @Setter
    private boolean aLarger;
    @Setter
    private boolean equal;
    @Setter
    private boolean zero;

    private final FourBitBus flagsBus;

    public FlagsRegister(FourBitBus flagsBus, Clock clock) {
        this.flagsBus = flagsBus;
        this.subscribe(clock);
    }

    @Override
    public void clkSet() {
        if(this.isSet()) {
            doSet();
        }
    }

    protected void doSet() {
        this.carryOut = (flagsBus.getData() & 0b1000) > 0;
        this.aLarger = (flagsBus.getData() & 0b0100) > 0;
        this.equal = (flagsBus.getData() & 0b0010) > 0;
        this.zero = (flagsBus.getData() & 0b0001) > 0;
    }
}
