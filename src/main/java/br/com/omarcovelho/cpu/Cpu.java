package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.Bus;
import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.common.Register;

public class Cpu {
    private final Register r0;
    private final Register r1;
    private final Register r2;
    private final Register r4;
    private final Alu alu;

    public Cpu(Bus bus, Clock clock) {
        this.r0 = new Register(bus, clock);
        this.r1 = new Register(bus, clock);
        this.r2 = new Register(bus, clock);
        this.r4 = new Register(bus, clock);
        this.alu = new Alu(clock, bus);
    }
}
