package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.Nibble;

public class Compare implements AluOperation {

    @Override
    public void execute(Alu alu) {
        alu.getAccBus().put(Byte.of(alu.getCommonBus().getValue().toInt() ^ alu.getTmp().getValue().toInt()));
        int isALarger = (alu.getCommonBus().getValue().toInt() > alu.getTmp().getValue().toInt()) ? A_LARGER_CODE : 0;
        int isEqual = alu.getCommonBus().getValue().toInt() == alu.getTmp().getValue().toInt() ? EQUAL_CODE : 0;
        alu.getFlagsBus().put(Nibble.of(isALarger | isEqual));
    }
}
