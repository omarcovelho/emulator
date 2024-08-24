package br.com.omarcovelho.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class RegisterTest {

  @Test
  public void shouldStartWithAllZeros() {
    Register register = new Register(new ByteBus("commonBus"), new Clock(), "register");
    assertThat(register.getValue(), equalTo(Byte.of(0)));
  }

  @Test
  public void shouldSetValueFromBus() {
    Clock clock = new Clock();
    ByteBus bus = new ByteBus("commonBu");
    Register register = new Register(bus, clock, "register");

    bus.put(255);
    register.setSet(true);
    clock.tick();

    assertThat(register.getValue(), equalTo(Byte.of(255)));
    assertThat(bus.getValue(), equalTo(255));
  }

  @Test
  public void shouldReadValueToBus() {
    Clock clock = new Clock();
    ByteBus bus = new ByteBus("commonBus");
    Register register = new Register(bus, clock, "register");

    bus.put(255);

    register.setEnable(true);
    clock.tick();

    assertThat(register.getValue(), equalTo(Byte.of(0)));
    assertThat(bus.getValue(), equalTo(0));
  }
}