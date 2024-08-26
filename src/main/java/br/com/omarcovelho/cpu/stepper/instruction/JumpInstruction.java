package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class JumpInstruction implements Instruction {
    @Override
    public boolean supports(Data instruction) {
        return 0b0011 == (instruction.toInt() >> 4);
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
            (ir, componentsRegistry) -> {
                componentsRegistry.get(ComponentType.IAR).setEnable(true);
                componentsRegistry.get(ComponentType.MAR).setSet(true);
            },
            (ir, componentsRegistry) -> {
                componentsRegistry.get(ComponentType.RAM).setEnable(true);
                componentsRegistry.get(ComponentType.IAR).setSet(true);
            }
        );
    }
}
