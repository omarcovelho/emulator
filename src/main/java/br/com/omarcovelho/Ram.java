package br.com.omarcovelho;

import lombok.Setter;

public class Ram {

  @Setter
  private Byte address = new Byte();
  private final Byte[] values = new Byte[256];

  public Byte read() {
    return values[address.toInt()];
  }

  public void set(Byte aByte) {
    values[address.toInt()] = aByte;
  }
}
