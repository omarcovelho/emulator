package br.com.omarcovelho.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bus {
  private Byte value = new Byte();

  public Byte getValue() {
    return Byte.of(value);
  }

  public void put(Byte value) {
    this.value = Byte.of(value);
  }
}
