package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;
import br.com.omarcovelho.cpu.alu.AluOperationFactory;

import java.util.Arrays;
import java.util.List;

public class ClearFlagInstruction implements Instruction {

    @Override
    public boolean supports(Data instruction) {
        return 0b0110 == (instruction.toInt() >> 4);
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
                (ir, componentsRegistry) -> {
                    componentsRegistry.getAlu().setOperation(AluOperationFactory.ADD);
                    componentsRegistry.getAlu().setBus1(true);
                    componentsRegistry.getAlu().disableA();
                    componentsRegistry.getAlu().getFlagsRegister().setSet(true);
                }
        );
    }
}
