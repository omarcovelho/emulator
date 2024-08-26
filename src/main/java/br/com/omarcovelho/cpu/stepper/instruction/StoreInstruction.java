package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class StoreInstruction implements Instruction {

    @Override
    public boolean supports(Data instruction) {
        return 0b0001 == (instruction.toInt() >> 4);
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
            (ir, componentsRegistry) -> {
                int addressRegister = ir.getValue().toInt() & 0b00001100;
                componentsRegistry.resolveRegister(addressRegister).setEnable(true);
                componentsRegistry.get(ComponentType.MAR).setSet(true);
            },
            (ir, componentsRegistry) -> {
                int targetRegister = ir.getValue().toInt() & 0b000000011;
                componentsRegistry.resolveRegister(targetRegister).setEnable(true);
                componentsRegistry.get(ComponentType.RAM).setSet(true);
            }
        );
    }
}
