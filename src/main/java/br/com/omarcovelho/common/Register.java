package br.com.omarcovelho.common;

import lombok.Getter;

@Getter
public class Register extends ControlledComponent implements Clockable {
  private Byte value = new Byte();
  private final Bus bus;

  public Register(Bus bus, Clock clock) {
    this.bus = bus;
    this.subscribe(clock);
  }

  @Override
  public void clkSet() {
    if(this.isSet()) {
      this.value = Byte.of(bus.getValue());
    }
  }

  @Override
  public void subscribe(Clock clock) {
    clock.register(this);
  }

  @Override
  public void clkEnable() {
    if(this.isEnable()) {
      this.bus.put(this.value);
    }
  }
}
