package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;

public class Add implements AluOperation {
    @Override
    public void execute(Alu alu) {
        int sum = (alu.isBus1() ? 0b00000001 : alu.getTmp().getValue().toInt()) + alu.getCommonBus().getValue().toInt() + (alu.isCarryIn() ? 1 : 0);
        alu.getAccBus().put(Byte.of(sum & 0b11111111));
        alu.setCarryOut((sum & 0b100000000) > 0);
    }
}
