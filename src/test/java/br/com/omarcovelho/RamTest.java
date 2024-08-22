package br.com.omarcovelho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RamTest {

  @Test
  public void shouldSetAndReadFromAddress() {
    Ram ram = new Ram();
    ram.setAddress(Byte.of(2));
    ram.set(Byte.of(2));
    Byte valueRead = ram.read();
    assertThat(valueRead.toInt(), equalTo(2));
  }

}