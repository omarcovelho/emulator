package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.Nibble;

public class Add implements AluOperation {

    @Override
    public void execute(Alu alu) {
        int sum = alu.getTmp().getValue().toInt()
                + alu.getCommonBus().getValue().toInt()
                + (shouldAddCarryIn(alu) ? 1 : 0);
        alu.getAccBus().put(Byte.of(sum & 0b11111111));
        alu.getFlagsBus().put(Nibble.of((sum & 0b100000000) > 0 ? CARRY_OUT_CODE : 0 ));
    }

    private static boolean shouldAddCarryIn(Alu alu) {
        return alu.isCarryIn() && !alu.isBus1();
    }
}
