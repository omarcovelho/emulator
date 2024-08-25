package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class InstructionTranslator {

    private final List<Instruction> instructionStepsMap;

    public InstructionTranslator() {
        this.instructionStepsMap = allInstructions();
    }

    private List<Instruction> allInstructions() {
        return Arrays.asList(
            new AluInstruction(),
            new DataInstruction(),
            new JumpInstruction(),
            new JumpRegisterInstruction(),
            new LoadInstruction(),
            new StoreInstruction(),
            new JumpOnFlagsInstruction(),
            new ClearFlagInstruction()
        );
    }


    public List<InstructionStep> translate(Data instruction) {
        return instructionStepsMap.stream().filter(i -> i.supports(instruction))
                .findFirst()
                .map(i -> i.getSteps(instruction))
                .orElseThrow(() -> new IllegalArgumentException("Instruction not implemented: [" + instruction + "]"));
    }
}
