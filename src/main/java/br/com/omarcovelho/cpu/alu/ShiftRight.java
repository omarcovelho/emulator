package br.com.omarcovelho.cpu.alu;

public class ShiftRight implements AluOperation {
    @Override
    public void execute(Alu alu) {
        int value = alu.getCommonBus().getValue();
        alu.getFlagsBus().put((value & 0b00000001) > 0 ? CARRY_OUT_CODE : 0);
        alu.getAccBus().put(value >> 1 | (alu.isCarryIn() ? 0b10000000 : 0));
    }
}
