package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.*;
import br.com.omarcovelho.cpu.alu.Alu;
import lombok.Getter;

import java.util.Map;

@Getter
public class Cpu {
    private final Register r0;
    private final Register r1;
    private final Register r2;
    private final Register r3;
    private final Alu alu;
    private final Register iar; //Instruction Address Register
    private final Register ir; //Instruction Register
    private final Stepper stepper;

    public Cpu(Bus bus, Clock clock) {
        this.r0 = new Register(bus, clock, "r0");
        this.r1 = new Register(bus, clock, "r1");
        this.r2 = new Register(bus, clock, "r2");
        this.r3 = new Register(bus, clock, "r3");
        this.alu = new Alu(clock, bus);
        this.iar = new Register(bus, clock, "iar");
        this.ir = new Register(bus, clock, "ir");
        this.stepper = new Stepper(clock, this);

        ComponentsRegistry.put(Map.ofEntries(
            Map.entry(ComponentType.R0, r0),
            Map.entry(ComponentType.R1, r1),
            Map.entry(ComponentType.R2, r2),
            Map.entry(ComponentType.R3, r3),
            Map.entry(ComponentType.IAR, iar),
            Map.entry(ComponentType.IR, ir)
        ));
    }

    public void printState() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("R0: %s\tR1: %s\t", r0, r1));
        buffer.append(String.format("R2: %s\tR3: %s\t", r2, r3));
        buffer.append(String.format("IAR: %s\tIR: %s", iar, ir));
        System.out.println("Registers");
        System.out.println(buffer);
        System.out.println("Stepper");
        stepper.printState();
    }
}
