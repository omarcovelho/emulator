package br.com.omarcovelho.cpu.alu;

public class Not implements AluOperation {
    @Override
    public void execute(Alu alu) {
        alu.getAccBus().put(~alu.getCommonBus().getValue());
    }
}
