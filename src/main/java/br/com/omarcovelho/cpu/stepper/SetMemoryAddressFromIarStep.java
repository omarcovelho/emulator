package br.com.omarcovelho.cpu.stepper;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.common.Register;
import br.com.omarcovelho.cpu.Cpu;
import br.com.omarcovelho.cpu.InstructionStep;
import br.com.omarcovelho.ram.RamAddress;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetMemoryAddressFromIarStep implements InstructionStep {

    private final Cpu cpu;

    @Override
    public void execute() {
        cpu.getAlu().setBus1(true);
        ((Register) ComponentsRegistry.get(ComponentType.IAR)).setEnable(true);
        ((RamAddress) ComponentsRegistry.get(ComponentType.MAR)).setSet(true);
        ((Register) ComponentsRegistry.get(ComponentType.ACC)).setSet(true);
    }
}
