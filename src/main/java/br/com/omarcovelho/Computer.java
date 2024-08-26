package br.com.omarcovelho;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ByteBus;
import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.cpu.Cpu;
import br.com.omarcovelho.ram.Ram;

public class Computer {
    private final Ram ram;
    private final Cpu cpu;

    private final ComponentsRegistry componentsRegistry;

    public Computer(Clock clock, Byte[] program) {
        ByteBus bus = new ByteBus("commonBus");
        this.componentsRegistry = new ComponentsRegistry();
        this.ram = new Ram(bus, clock, program, componentsRegistry);
        this.cpu = new Cpu(bus, clock, componentsRegistry);
    }

    public void printState() {
        System.out.println("======== Computer State ========");
        cpu.printState();
        ram.printState();
        System.out.println("======== End of Computer State ========");
    }
}
