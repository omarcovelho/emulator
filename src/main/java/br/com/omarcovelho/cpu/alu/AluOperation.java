package br.com.omarcovelho.cpu.alu;

public interface AluOperation {

    int CARRY_OUT_CODE = 0b1000;
    int A_LARGER_CODE = 0b0100;
    int EQUAL_CODE = 0b0010;
    void execute(Alu alu);
}
