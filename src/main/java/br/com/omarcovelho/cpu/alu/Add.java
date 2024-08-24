package br.com.omarcovelho.cpu.alu;

public class Add implements AluOperation {

    @Override
    public void execute(Alu alu) {
        int sum = (alu.isBus1() ? 0b00000001 : alu.getTmp().getValue().toInt()) + alu.getCommonBus().getValue() + (alu.isCarryIn() ? 1 : 0);
        alu.getAccBus().put(sum & 0b11111111);
        alu.getFlagsBus().put((sum & 0b100000000) > 0 ? CARRY_OUT_CODE : 0 );
    }
}
