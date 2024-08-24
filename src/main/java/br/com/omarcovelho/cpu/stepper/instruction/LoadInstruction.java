package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class LoadInstruction implements Instruction {

    @Override
    public boolean supports(Data instruction) {
        return (0b0000 & (instruction.toInt() >> 4)) > 0;
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
            (ir) -> {
                int addressRegister = ir.getValue().toInt() & 0b00001100;
                ComponentsRegistry.resolveRegister(addressRegister).setEnable(true);
                ComponentsRegistry.get(ComponentType.MAR).setSet(true);
            },
            (ir) -> {
                int targetRegister = ir.getValue().toInt() & 0b000000011;
                ComponentsRegistry.get(ComponentType.RAM).setEnable(true);
                ComponentsRegistry.resolveRegister(targetRegister).setSet(true);
            }
        );
    }
}
