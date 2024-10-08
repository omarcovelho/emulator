package br.com.omarcovelho.cpu.stepper;

import br.com.omarcovelho.common.*;
import br.com.omarcovelho.cpu.Cpu;
import br.com.omarcovelho.cpu.InstructionStep;
import br.com.omarcovelho.cpu.alu.AluOperationFactory;
import br.com.omarcovelho.cpu.alu.RegisterSubscriber;
import br.com.omarcovelho.cpu.stepper.fetch.ReadInstructionToIr;
import br.com.omarcovelho.cpu.stepper.fetch.SetMemoryAddressFromIarStep;
import br.com.omarcovelho.cpu.stepper.fetch.SetNextInstruction;
import br.com.omarcovelho.cpu.stepper.instruction.InstructionTranslator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Stepper implements Clockable, RegisterSubscriber {

    private final LinkedList<InstructionStep> steps;
    private final Cpu cpu;
    private int stepIndex = 0;
    private final ByteRegister ir;
    private final InstructionTranslator instructionTranslator = new InstructionTranslator();
    private final ComponentsRegistry componentsRegistry;
    public Stepper(Clock clock, Cpu cpu, ByteRegister ir, ComponentsRegistry componentsRegistry) {
        this.cpu = cpu;
        this.steps = initialStateSteps();
        this.subscribe(clock);
        this.ir = ir;
        this.ir.register(this);
        this.componentsRegistry = componentsRegistry;
    }

    private LinkedList<InstructionStep> initialStateSteps() {
        return new LinkedList<>(Arrays.asList(
            new SetMemoryAddressFromIarStep(this.cpu),
            new ReadInstructionToIr(),
            new SetNextInstruction()
        ));
    }

    @Override
    public void preClock() {
        if (stepIndex < steps.size()) {
            InstructionStep step = steps.get(stepIndex++);
            System.out.println("Executing " + step.getClass().getSimpleName());
            step.execute(ir, componentsRegistry);
        } else {
            stepIndex = 0;
        }
    }

    @Override
    public void clkFinish() {
        cpu.getAlu().setBus1(false);
        cpu.getAlu().getFlagsRegister().setSet(false);
        cpu.getAlu().setOperation(AluOperationFactory.ADD);
        componentsRegistry.getAll()
                .forEach(ControlledComponent::clearFlags);
        componentsRegistry.getRegisters()
                .forEach(ControlledComponent::clearFlags);
    }

    @Override
    public void onRegisterChange(AbstractRegister ir) {
        this.updateSteps(instructionTranslator.translate(ir.getValue()));
    }

    private void updateSteps(List<InstructionStep> steps) {
        this.steps.clear();
        this.steps.addAll(initialStateSteps());
        this.steps.addAll(steps);
    }
}
