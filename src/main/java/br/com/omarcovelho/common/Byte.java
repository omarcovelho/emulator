package br.com.omarcovelho.common;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Byte extends Data {

  private Byte(int value) {
    super(value, 8);
  }

  public static Byte of(int value) {
    return new Byte(value);
  }

  public static Byte of(Byte value) {
    return new Byte(value.toInt());
  }

  public static Byte of(Data value) {
    return new Byte(value.toInt());
  }
}
