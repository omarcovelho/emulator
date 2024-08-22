package br.com.omarcovelho.cpu.stepper;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.common.Register;
import br.com.omarcovelho.cpu.InstructionStep;
import br.com.omarcovelho.ram.RamMemory;

public class ReadInstructionToIr implements InstructionStep {
    @Override
    public void execute() {
        ((RamMemory) ComponentsRegistry.get(ComponentType.RAM)).setEnable(true);
        ((Register) ComponentsRegistry.get(ComponentType.IR)).setSet(true);
    }
}
