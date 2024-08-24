package br.com.omarcovelho.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ByteTest {

  @Test
  public void shouldThrowExceptionForValueHigherThan8Bit() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
        () -> Byte.of(256));

    assertThat(ex.getMessage(), equalTo("this datatype has [8] bits and does not support [256]. max value is [255]"));
  }
}