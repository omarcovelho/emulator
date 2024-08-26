package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class Alu implements BusSubscriber, RegisterSubscriber {
    private final Bus1Register tmp;
    private final ByteRegister acc;
    private final ByteBus accBus;
    private final ByteBus commonBus;
    private AluOperation operation = AluOperationFactory.getOperation(0b000);
    private final FourBitBus flagsBus;
    private final FlagsRegister flagsRegister;

    public Alu(Clock clock, ByteBus bus, ComponentsRegistry componentsRegistry) {
        this.accBus = new ByteBus("accBus");
        this.commonBus = bus;
        this.acc = new DualBusByteRegister(bus, clock, "acc", accBus);
        this.tmp = new Bus1Register(bus, clock, "tmp");
        this.flagsBus = new FourBitBus("flags");
        this.flagsRegister = new FlagsRegister(flagsBus, clock);

        this.commonBus.subscribe(this);

        componentsRegistry.put(Map.ofEntries(
            Map.entry(ComponentType.TMP, tmp),
            Map.entry(ComponentType.ACC, acc)
        ));
    }

    public void setOperation(int code) {
        operation = AluOperationFactory.getOperation(code);
        operation.execute(this);
    }

    @Override
    public void onBusChange() {
        operation.execute(this);
    }

    public void setBus1(boolean bool) {
        this.tmp.setBypass(bool);
    }

    @Override
    public void onRegisterChange(AbstractRegister register) {
        operation.execute(this);
    }

    public void printState() {
        System.out.println("Alu Flags: " + getFlagsRegister());
    }

    public boolean isCarryIn() {
        return getFlagsRegister().isCarryOut();
    }

    public boolean isBus1() {
        return this.tmp.isBypass();
    }

    public void disableA() {
        this.commonBus.put(Byte.of(0));
    }
}
