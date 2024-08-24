package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;

public class ShiftRight implements AluOperation {
    @Override
    public void execute(Alu alu) {
        int value = alu.getCommonBus().getValue().toInt();
        alu.getFlagsBus().put((value & 0b00000001) > 0 ? CARRY_OUT_CODE : 0);
        alu.getAccBus().put(Byte.of(value >> 1 | (alu.isCarryIn() ? 0b10000000 : 0)));
    }
}
