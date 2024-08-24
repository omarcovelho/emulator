package br.com.omarcovelho.cpu.alu;

public class Or implements AluOperation {
    @Override
    public void execute(Alu alu) {
        alu.getAccBus().put(alu.getCommonBus().getValue() | alu.getTmp().getValue().toInt());
    }
}
