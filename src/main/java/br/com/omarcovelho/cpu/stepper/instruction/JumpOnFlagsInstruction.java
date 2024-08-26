package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class JumpOnFlagsInstruction implements Instruction {
    @Override
    public boolean supports(Data instruction) {
        return 0b0101 == (instruction.toInt() >> 4);
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
                (ir, componentsRegistry) -> {
                    componentsRegistry.getAlu().setBus1(true);
                    componentsRegistry.get(ComponentType.IAR).setEnable(true);
                    componentsRegistry.get(ComponentType.MAR).setSet(true);
                    componentsRegistry.get(ComponentType.ACC).setSet(true);
                },
                (ir, componentsRegistry) -> {
                    componentsRegistry.get(ComponentType.ACC).setEnable(true);
                    componentsRegistry.get(ComponentType.IAR).setSet(true);
                },
                (ir, componentsRegistry) -> {
                    int flagsToCompare = ir.getValue().toInt() & 0b00001111;
                    if((flagsToCompare & componentsRegistry.getAlu().getFlagsRegister().getValue().toInt()) != 0) {
                        componentsRegistry.get(ComponentType.RAM).setEnable(true);
                        componentsRegistry.get(ComponentType.IAR).setSet(true);
                    }
                }
        );
    }
}
