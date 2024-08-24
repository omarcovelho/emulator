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

    public Cpu(ByteBus bus, Clock clock) {
        this.r0 = new ByteRegister(bus, clock, "r0");
        this.r1 = new ByteRegister(bus, clock, "r1");
        this.r2 = new ByteRegister(bus, clock, "r2");
        this.r3 = new ByteRegister(bus, clock, "r3");
        this.alu = new Alu(clock, bus);
        this.iar = new ByteRegister(bus, clock, "iar");
        this.ir = new ByteRegister(bus, clock, "ir");
        this.stepper = new Stepper(clock, this, ir);

        ComponentsRegistry.put(Map.ofEntries(
            Map.entry(ComponentType.IAR, iar),
            Map.entry(ComponentType.IR, ir)
        ));
        ComponentsRegistry.setAlu(alu);
        ComponentsRegistry.setRegisterDecoder(Arrays.asList(
            r0, r1, r2, r3
        ));
    }

    public void printState() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("R0: %s\tR1: %s\t", r0, r1));
        buffer.append(String.format("R2: %s\tR3: %s\t", r2, r3));
        buffer.append(String.format("IAR: %s\tIR: %s\t", iar, ir));
        buffer.append(String.format("ACC: %s", alu.getAcc()));
        System.out.println("Registers");
        System.out.println(buffer);
        alu.printState();
        System.out.println("Stepper");
    }
}
