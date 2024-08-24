package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.common.Clockable;
import br.com.omarcovelho.common.FourBitBus;
import br.com.omarcovelho.common.NibbleRegister;
import lombok.Getter;

@Getter
public class FlagsRegister extends NibbleRegister implements Clockable {

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
    public boolean isCarryOut() {
        return (getValue().toInt() & 0b1000) > 0;
    }

    public boolean isALarger() {
        return (getValue().toInt() & 0b0100) > 0;
    }

    public boolean isEqual() {
        return (getValue().toInt() & 0b0010) > 0;
    }

    public boolean isZero() {
        return (getValue().toInt() & 0b0001) > 0;
    }
}
