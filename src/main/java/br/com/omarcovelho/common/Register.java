package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.RegisterSubscriber;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Register extends ControlledComponent implements Clockable {
  private Byte value = new Byte();
  private final Bus bus;
  private final String id;

  private final List<RegisterSubscriber> watchers = new ArrayList<>();

  public Register(Bus bus, Clock clock, String id) {
    this.bus = bus;
    this.id = id;
    this.subscribe(clock);
  }

  @Override
  public void clkSet() {
    if(this.isSet()) {
      this.value = Byte.of(bus.getValue());
      watchers.forEach(RegisterSubscriber::onRegisterChange);
    }
  }

  @Override
  public void clkEnable() {
    if(this.isEnable()) {
      this.bus.put(this.value);
    }
  }

  @Override
  public String toString() {
    return Integer.toBinaryString(this.value.toInt());
  }

  public void subscribe(RegisterSubscriber subscriber) {
    this.watchers.add(subscriber);
  }
}
