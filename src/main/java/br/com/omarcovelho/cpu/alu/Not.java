package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;

public class Not implements AluOperation {
    @Override
    public void execute(Alu alu) {
        alu.getAccBus().put(Byte.of(~alu.getCommonBus().getValue().toInt()));
    }
}
