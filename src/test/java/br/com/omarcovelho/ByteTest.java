package br.com.omarcovelho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ByteTest {

  @Test
  public void shouldCreateByteFromInteger() {
    Byte aByte = Byte.of(10);
    assertThat(aByte.getBits(),
        equalTo(new boolean[]{false, false, false, false, true, false, true, false}));
  }

  @Test
  public void shouldThrowExceptionForValueHigherThan8Bit() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
        () -> Byte.of(256));

    assertThat(ex.getMessage(), equalTo("Value higher than 8 bit"));
  }

  @Test
  public void shouldConvertToInteger() {
    Byte aByte = new Byte(new boolean[]{false, false, false, false, true, false, true, false});
    assertThat(aByte.toInt(), equalTo(10));
  }
}