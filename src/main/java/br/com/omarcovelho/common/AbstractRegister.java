package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.RegisterSubscriber;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AbstractRegister extends ControlledComponent implements Clockable {
    protected Data value;
    protected final AbstractBus bus;
    protected final String id;
    private final List<RegisterSubscriber> listeners = new ArrayList<>();

    public AbstractRegister(Data value, AbstractBus bus, String id, Clock clock) {
        this.value = value;
        this.bus = bus;
        this.id = id;
        this.subscribe(clock);
    }

    @Override
    public void clkSet() {
        if(this.isSet()) {
            doSet();
            listeners.forEach(l -> l.onRegisterChange(this));
        }
    }
    protected void doSet() {
        this.value = Byte.of(bus.getValue());
    }

    @Override
    public void clkEnable() {
        if(this.isEnable()) {
            doEnable();
        }
    }

    protected void doEnable() {
        this.bus.put(this.value.toInt());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public void register(RegisterSubscriber subscriber) {
        this.listeners.add(subscriber);
    }
}
