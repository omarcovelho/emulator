package br.com.omarcovelho.cpu.stepper;

import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.common.Clockable;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.common.ControlledComponent;
import br.com.omarcovelho.cpu.Cpu;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Stepper implements Clockable {

    private final LinkedList<InstructionStep> steps;
    private final Cpu cpu;
    private Iterator<InstructionStep> stepsIterator;
    public Stepper(Clock clock, Cpu cpu) {
        this.cpu = cpu;
        this.steps = initialStateSteps();
        this.subscribe(clock);
        stepsIterator = this.steps.iterator();
    }

    private LinkedList<InstructionStep> initialStateSteps() {
        return new LinkedList<>(Arrays.asList(
            new SetMemoryAddressFromIarStep(this.cpu),
            new ReadInstructionToIr(),
            new SetNextInstruction()
        ));
    }

    public void updateSteps(List<InstructionStep> steps) {
        this.steps.clear();
        this.steps.addAll(initialStateSteps());
        this.steps.addAll(steps);
    }

    @Override
    public void preClock() {
        if (stepsIterator.hasNext()) {
            InstructionStep step = stepsIterator.next();
            System.out.println("Executing " + step.getClass().getSimpleName());
            step.execute();
        } else {
            stepsIterator = steps.iterator();
        }
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
