package br.com.omarcovelho;

import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.ArrayUtils;

@NoArgsConstructor
@EqualsAndHashCode
public class Byte {
  private boolean[] bits = new boolean[8];

  public Byte(boolean[] flags) {
    this.bits = Arrays.copyOf(flags, flags.length);
  }

  public boolean[] getBits() {
    return Arrays.copyOf(bits, bits.length);
  }

  public int toInt(){
    int n = 0;
    for (boolean b : this.bits)
      n = (n << 1) | (b ? 1 : 0);
    return n;
  }

  public static Byte of(int value) {
    if(value > 255) {
      throw new IllegalArgumentException("Value higher than 8 bit");
    }
    boolean [] flags = new boolean[8];
    for (int i = 0; i < flags.length; ++i) {
      flags[i] = (value & (1 << i)) != 0;
    }
    ArrayUtils.reverse(flags);
    return new Byte(flags);
  }

  @Override
  public String toString() {
    String value = "";
    for (boolean bit : this.bits) {
      value += (bit ? "1" : "0");
    }

    return value;
  }
}
