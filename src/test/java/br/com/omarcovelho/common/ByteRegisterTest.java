package br.com.omarcovelho.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ByteRegisterTest {

  @Test
  public void shouldStartWithAllZeros() {
    ByteRegister register = new ByteRegister(new ByteBus("commonBus"), new Clock(), "register");
    assertThat(register.getValue(), equalTo(Byte.of(0)));
  }

  @Test
  public void shouldSetValueFromBus() {
    Clock clock = new Clock();
    ByteBus bus = new ByteBus("commonBu");
    ByteRegister register = new ByteRegister(bus, clock, "register");

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
    ByteRegister register = new ByteRegister(bus, clock, "register");

    bus.put(255);

    register.setEnable(true);
    clock.tick();

    assertThat(register.getValue(), equalTo(Byte.of(0)));
    assertThat(bus.getValue(), equalTo(0));
  }
}