package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Alu implements BusSubscriber, RegisterSubscriber {
    private final ByteRegister tmp;
    private final ByteRegister acc;
    @Setter
    private boolean carryIn;
    private boolean bus1;
    private final ByteBus accBus;
    private final ByteBus commonBus;
    private AluOperation operation = AluOperationFactory.getOperation(0b000);
    private final FourBitBus flagsBus;
    private final FlagsRegister flagsRegister;

    public Alu(Clock clock, ByteBus bus) {
        this.accBus = new ByteBus("accBus");
        this.commonBus = bus;
        this.acc = new DualBusByteRegister(bus, clock, "acc", accBus);
        this.tmp = new ByteRegister(bus, clock, "tmp");
        this.flagsBus = new FourBitBus("flags");
        this.flagsRegister = new FlagsRegister(flagsBus, clock);

        this.commonBus.subscribe(this);

        ComponentsRegistry.put(Map.ofEntries(
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

    public void setBus1(boolean bus1) {
        this.bus1 = bus1;
        operation.execute(this);
    }

    @Override
    public void onRegisterChange(AbstractRegister register) {
        operation.execute(this);
    }
}
