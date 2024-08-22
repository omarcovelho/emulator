package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Alu implements BusSubscriber, RegisterSubscriber {
    private final Register tmp;
    private final Register acc;
    @Setter
    private boolean carryIn;
    @Setter
    private boolean carryOut;
    @Setter
    private boolean aLarger;
    @Setter
    private boolean equal;
    @Setter
    private boolean zero;
    private boolean bus1;
    private final Bus accBus;
    private final Bus commonBus;
    private AluOperation operation = AluOperationFactory.getOperation(0b000);

    public Alu(Clock clock, Bus bus) {
        this.tmp = new Register(bus, clock, "tmp");
        this.accBus = new Bus("accBus");
        this.acc = new Register(accBus, clock, "acc");
        this.commonBus = bus;

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
    public void onRegisterChange() {
        operation.execute(this);
    }
}
