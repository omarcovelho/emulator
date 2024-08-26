package br.com.omarcovelho.cpu.stepper.fetch;

import br.com.omarcovelho.common.ByteRegister;
import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.cpu.InstructionStep;

public class ReadInstructionToIr implements InstructionStep {
    @Override
    public void execute(ByteRegister ir, ComponentsRegistry componentsRegistry) {
        componentsRegistry.get(ComponentType.RAM).setEnable(true);
        componentsRegistry.get(ComponentType.IR).setSet(true);
    }
}
