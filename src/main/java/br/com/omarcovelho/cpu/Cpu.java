package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.*;
import br.com.omarcovelho.cpu.alu.Alu;
import br.com.omarcovelho.cpu.stepper.Stepper;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Cpu {
    private final ByteRegister r0;
    private final ByteRegister r1;
    private final ByteRegister r2;
    private final ByteRegister r3;
    private final Alu alu;
    private final ByteRegister iar; //Instruction Address Register
    private final ByteRegister ir; //Instruction Register
    private final Stepper stepper;

    public Cpu(ByteBus bus, Clock clock, ComponentsRegistry componentsRegistry) {
        this.r0 = new ByteRegister(bus, clock, "r0");
        this.r1 = new ByteRegister(bus, clock, "r1");
        this.r2 = new ByteRegister(bus, clock, "r2");
        this.r3 = new ByteRegister(bus, clock, "r3");
        this.alu = new Alu(clock, bus, componentsRegistry);
        this.iar = new ByteRegister(bus, clock, "iar");
        this.ir = new ByteRegister(bus, clock, "ir");
        this.stepper = new Stepper(clock, this, ir, componentsRegistry);

        componentsRegistry.put(Map.ofEntries(
            Map.entry(ComponentType.IAR, iar),
            Map.entry(ComponentType.IR, ir)
        ));
        componentsRegistry.setAlu(alu);
        componentsRegistry.setRegisterDecoder(Arrays.asList(
            r0, r1, r2, r3
        ));
    }

    public void printState() {
        String text = String.format("R0: %s\tR1: %s\t", r0, r1) +
                String.format("R2: %s\tR3: %s\t", r2, r3) +
                String.format("IAR: %s\tIR: %s\t", iar, ir) +
                String.format("ACC: %s", alu.getAcc());
        System.out.println("Registers");
        System.out.println(text);
        alu.printState();
        System.out.println("Stepper");
    }
}
