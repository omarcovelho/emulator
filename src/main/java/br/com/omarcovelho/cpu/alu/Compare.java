package br.com.omarcovelho.cpu.alu;

public class Compare implements AluOperation {

    @Override
    public void execute(Alu alu) {
        alu.getAccBus().put(alu.getCommonBus().getValue() ^ alu.getTmp().getValue().toInt());
        int isALarger = (alu.getCommonBus().getValue() > alu.getTmp().getValue().toInt()) ? A_LARGER_CODE : 0;
        int isEqual = alu.getCommonBus().getValue() == alu.getTmp().getValue().toInt() ? EQUAL_CODE : 0;
        alu.getFlagsBus().put(isALarger | isEqual);
    }
}
