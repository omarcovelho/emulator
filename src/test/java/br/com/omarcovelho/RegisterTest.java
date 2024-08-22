package br.com.omarcovelho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

import org.junit.jupiter.api.Test;

class RegisterTest {

  @Test
  public void shouldStartWithAllZeros() {
    Register register = new Register(new Bus(), new Clock());
    assertThat(register.getValue(), equalTo(Byte.of(0)));
  }

  @Test
  public void shouldSetValueFromBus() {
    Clock clock = new Clock();
    Bus bus = new Bus();
    Register register = new Register(bus, clock);

    bus.put(Byte.of(255));
    register.setSet(true);
    clock.tick();

    assertThat(register.getValue(), equalTo(Byte.of(255)));
    assertThat(bus.getValue(), equalTo(Byte.of(255)));
  }

  @Test
  public void shouldReadValueToBus() {
    Clock clock = new Clock();
    Bus bus = new Bus();
    Register register = new Register(bus, clock);

    bus.put(Byte.of(255));

    register.setEnable(true);
    clock.tick();

    assertThat(register.getValue(), equalTo(Byte.of(0)));
    assertThat(bus.getValue(), equalTo(Byte.of(0)));
  }
}