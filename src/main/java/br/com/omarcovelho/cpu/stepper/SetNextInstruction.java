package br.com.omarcovelho.cpu.stepper;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.common.Register;
import br.com.omarcovelho.cpu.InstructionStep;

public class SetNextInstruction implements InstructionStep {
    @Override
    public void execute() {
        ((Register) ComponentsRegistry.get(ComponentType.ACC)).setEnable(true);
        ((Register) ComponentsRegistry.get(ComponentType.IAR)).setSet(true);
    }
}
