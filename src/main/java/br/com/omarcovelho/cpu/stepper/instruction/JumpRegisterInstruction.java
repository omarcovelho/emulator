package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class JumpRegisterInstruction implements Instruction {
    @Override
    public boolean supports(Byte instruction) {
        return (0b0011 & (instruction.toInt() >> 4)) > 0;
    }

    @Override
    public List<InstructionStep> getSteps(Byte instruction) {
        return Arrays.asList(
            (ir) -> {
                int addressRegister = ir.getValue().toInt() & 0b00000011;
                ComponentsRegistry.resolveRegister(addressRegister).setEnable(true);
                ComponentsRegistry.get(ComponentType.IAR).setSet(true);
            }
        );
    }
}