package br.com.omarcovelho.cpu.stepper.fetch;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.common.Register;
import br.com.omarcovelho.cpu.Cpu;
import br.com.omarcovelho.cpu.InstructionStep;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetMemoryAddressFromIarStep implements InstructionStep {

    private final Cpu cpu;

    @Override
    public void execute(Register ir) {
        ComponentsRegistry.getAlu().setBus1(true);
        ComponentsRegistry.get(ComponentType.IAR).setEnable(true);
        ComponentsRegistry.get(ComponentType.MAR).setSet(true);
        ComponentsRegistry.get(ComponentType.ACC).setSet(true);
    }
}
