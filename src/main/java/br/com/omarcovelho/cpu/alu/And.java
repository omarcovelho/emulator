package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;

public class And implements AluOperation {
    @Override
    public void execute(Alu alu) {
        alu.getAccBus().put(Byte.of(alu.getCommonBus().getValue().toInt() & alu.getTmp().getValue().toInt()));
    }
}
