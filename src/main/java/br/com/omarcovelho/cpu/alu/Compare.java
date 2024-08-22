package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;

public class Compare implements AluOperation {

    @Override
    public void execute(Alu alu) {
        alu.getAccBus().put(Byte.of(alu.getCommonBus().getValue().toInt() ^ alu.getTmp().getValue().toInt()));
        alu.setALarger(alu.getCommonBus().getValue().toInt() > alu.getTmp().getValue().toInt());
        alu.setEqual(alu.getCommonBus().getValue().equals(alu.getTmp().getValue()));
    }
}
