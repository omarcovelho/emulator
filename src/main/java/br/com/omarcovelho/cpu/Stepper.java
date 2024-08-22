package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.*;
import br.com.omarcovelho.ram.RamAddress;
import br.com.omarcovelho.ram.RamMemory;

import java.util.LinkedList;

public class Stepper implements Clockable {

    private final LinkedList<InstructionStep> steps;
    private final Cpu cpu;
    public Stepper(Clock clock, Cpu cpu) {
        this.steps = initialStateSteps();
        this.cpu = cpu;
        this.subscribe(clock);
    }

    private LinkedList<InstructionStep> initialStateSteps() {
        LinkedList<InstructionStep> steps = new LinkedList<>();
        steps.add(() -> {
            cpu.getAlu().setBus1(true);
            ((Register)ComponentsRegistry.get(ComponentType.IAR)).setEnable(true);
            ((RamAddress)ComponentsRegistry.get(ComponentType.MAR)).setSet(true);
            ((Register)ComponentsRegistry.get(ComponentType.ACC)).setSet(true);
        });
        steps.add(() -> {
            ((RamMemory)ComponentsRegistry.get(ComponentType.RAM)).setEnable(true);
            ((Register)ComponentsRegistry.get(ComponentType.IR)).setSet(true);
        });
        steps.add(() -> {
            ((Register)ComponentsRegistry.get(ComponentType.ACC)).setEnable(true);
            ((Register)ComponentsRegistry.get(ComponentType.IAR)).setSet(true);
        });

        return steps;
    }

    @Override
    public void preClock() {
        steps.pop().execute();
    }

    @Override
    public void clkFinish() {
        cpu.getAlu().setBus1(false);
        ComponentsRegistry.getAll()
                .forEach(ControlledComponent::clearFlags);
    }

    public void printState() {

    }
}
