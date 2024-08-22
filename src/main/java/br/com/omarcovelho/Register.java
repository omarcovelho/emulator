package br.com.omarcovelho;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Register extends ControlledComponent implements Clockeable {
  private Byte value = new Byte();
  private final Bus bus;

  public Register(Bus bus, Clock clock) {
    this.bus = bus;
    this.subscribe(clock);
  }

  @Override
  public void set() {
    if(this.isSet()) {
      this.value = Byte.of(bus.getValue());
    }
  }

  @Override
  public void subscribe(Clock clock) {
    clock.register(this);
  }

  @Override
  public void enable() {
    if(this.isEnable()) {
      this.bus.put(this.value);
    }
  }
}
