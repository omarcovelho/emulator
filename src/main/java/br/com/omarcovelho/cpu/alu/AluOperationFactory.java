package br.com.omarcovelho.cpu.alu;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AluOperationFactory {

    public static final int ADD = 0b000;
    public static final int SHR = 0b001;
    public static final int SHL = 0b010;
    public static final int NOT = 0b011;
    public static final int AND = 0b100;
    public static final int OR = 0b101;
    public static final int XOR = 0b110;
    public static final int CMP = 0b111;

    private static final List<AluOperation> operations = Arrays.asList(
        new Add(),
        new ShiftLeft(),
        new ShiftRight(),
        new Not(),
        new And(),
        new Or(),
        new Xor(),
        new Compare()
    );

    public static final AluOperation getOperation(int code) {
        try {
            return operations.get(code);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("No operation for code: [" +  Integer.toBinaryString(code) + "]");
        }
    }

}
