package br.com.omarcovelho;

public class Computer {
    private final Ram ram;
    private final Cpu cpu;

    public Computer(Clock clock, Bus bus) {
        this.ram = new Ram(bus, clock);
        this.cpu = new Cpu(bus, clock);
    }
}
