package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.Nibble;

public class ShiftLeft implements AluOperation {
    @Override
    public void execute(Alu alu) {
        int value = alu.getCommonBus().getValue().toInt();
        alu.getFlagsBus().put(Nibble.of((value & 0b10000000) > 0 ? CARRY_OUT_CODE : 0));
        int binResult = alu.getCommonBus().getValue().toInt() << 1 | (alu.isCarryIn() ? 0b00000001 : 0);
        alu.getAccBus().put(Byte.of(binResult & 0b11111111));
    }
}
