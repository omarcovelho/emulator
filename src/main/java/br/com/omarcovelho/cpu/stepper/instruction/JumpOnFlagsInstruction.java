package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
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
                (ir) -> {
                    ComponentsRegistry.getAlu().setBus1(true);
                    ComponentsRegistry.get(ComponentType.IAR).setEnable(true);
                    ComponentsRegistry.get(ComponentType.MAR).setSet(true);
                    ComponentsRegistry.get(ComponentType.ACC).setSet(true);
                },
                (ir) -> {
                    ComponentsRegistry.get(ComponentType.ACC).setEnable(true);
                    ComponentsRegistry.get(ComponentType.IAR).setSet(true);
                },
                (ir) -> {
                    int flagsToCompare = ir.getValue().toInt() & 0b00001111;
                    if((flagsToCompare & ComponentsRegistry.getAlu().getFlagsRegister().getValue().toInt()) != 0) {
                        ComponentsRegistry.get(ComponentType.RAM).setEnable(true);
                        ComponentsRegistry.get(ComponentType.IAR).setSet(true);
                    }
                }
        );
    }
}
