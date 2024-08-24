package br.com.omarcovelho.common;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

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
}
