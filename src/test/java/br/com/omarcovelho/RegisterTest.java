package br.com.omarcovelho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

import org.junit.jupiter.api.Test;

class RegisterTest {

  @Test
  public void shouldStartWithAllZeros() {
    Register register = new Register(new Bus());
    assertThat(register.getValue(), equalTo(Byte.of(0)));
  }

  @Test
  public void shouldSetValueFromBus() {
    Bus bus = new Bus();
    Register register = new Register(bus);
    bus.put(Byte.of(255));
    register.set();
    bus.put(Byte.of(0));
    register.enable();

    assertThat(register.getValue(), equalTo(Byte.of(255)));
    assertThat(bus.getValue(), equalTo(Byte.of(255)));
  }
}