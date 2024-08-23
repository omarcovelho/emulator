package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.RegisterSubscriber;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Register extends ControlledComponent implements Clockable {
  protected Byte value = new Byte();
  private final Bus bus;
  private final String id;
  private final List<RegisterSubscriber> listeners = new ArrayList<>();

  public Register(Bus bus, Clock clock, String id) {
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
    this.bus.put(this.value);
  }

  @Override
  public String toString() {
    return this.value.toString();
  }

  public void register(RegisterSubscriber subscriber) {
    this.listeners.add(subscriber);
  }
}
