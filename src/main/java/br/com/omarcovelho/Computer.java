package br.com.omarcovelho;

import br.com.omarcovelho.common.Bus;
import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.cpu.Cpu;
import br.com.omarcovelho.ram.Ram;

public class Computer {
    private final Ram ram;
    private final Cpu cpu;

    public Computer(Clock clock, Bus bus) {
        this.ram = new Ram(bus, clock);
        this.cpu = new Cpu(bus, clock);
    }

    public void printState() {
        System.out.println("======== Computer State ========");
        cpu.printState();
        ram.printState();
        System.out.println("======== End of Computer State ========");
    }
}
