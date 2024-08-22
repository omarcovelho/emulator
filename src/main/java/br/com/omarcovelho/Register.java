package br.com.omarcovelho;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Register {
  private Byte value = new Byte();
  private final Bus bus;

  public void set() {
    this.value = Byte.of(bus.getValue());
  }

  public void enable() {
    this.bus.put(this.value);
  }
}
