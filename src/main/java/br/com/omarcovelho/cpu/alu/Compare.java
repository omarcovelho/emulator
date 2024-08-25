package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Nibble;

public class Compare implements AluOperation {

    @Override
    public void execute(Alu alu) {
        int isALarger = (alu.getCommonBus().getValue().toInt() > alu.getTmp().getValue().toInt()) ? A_LARGER_CODE : 0;
        int isEqual = alu.getCommonBus().getValue().toInt() == alu.getTmp().getValue().toInt() ? EQUAL_CODE : 0;
        int carryOut = alu.getFlagsRegister().isCarryOut() ? 0b1000 : 0;
        alu.getFlagsBus().put(Nibble.of(carryOut | isALarger | isEqual));
    }
}
