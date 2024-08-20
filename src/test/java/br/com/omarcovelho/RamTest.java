package br.com.omarcovelho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class RamTest {

  @Test
  public void shouldSetAddress() {
    Bus bus = new Bus();
    Ram ram = new Ram(new RamAddress(bus), new RamMemory(bus));
    bus.put(Byte.of(2));
    ram.setAddress();
    assertThat(ram.getAddress().getValue(), equalTo(Byte.of(2)));
  }

  @Test
  public void shouldSetMemoryValue() {
    Bus bus = new Bus();
    Ram ram = new Ram(new RamAddress(bus), new RamMemory(bus));
    bus.put(Byte.of(2));
    ram.setAddress();
    bus.put(Byte.of(255));
    ram.setMemory();
    bus.put(Byte.of(0));
    ram.enableMemory();
    assertThat(bus.getValue(), equalTo(Byte.of(255)));
  }

}