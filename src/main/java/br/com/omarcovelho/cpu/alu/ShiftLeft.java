package br.com.omarcovelho.cpu.alu;

public class ShiftLeft implements AluOperation {
    @Override
    public void execute(Alu alu) {
        int value = alu.getCommonBus().getValue();
        alu.getFlagsBus().put((value & 0b10000000) > 0 ? CARRY_OUT_CODE : 0);
        int binResult = alu.getCommonBus().getValue() << 1 | (alu.isCarryIn() ? 0b00000001 : 0);
        alu.getAccBus().put(binResult & 0b11111111);
    }
}
