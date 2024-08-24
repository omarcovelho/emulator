package br.com.omarcovelho.ram;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ByteBus;
import br.com.omarcovelho.common.Clock;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class RamTest {

  @Test
  public void shouldSetAddress() {
    Clock clock = new Clock();
    ByteBus bus = new ByteBus("commonBus");
    Ram ram = new Ram(bus, clock, new Byte[256]);
    ram.subscribe(clock);

    bus.put(Byte.of(2));
    ram.setAddress(true);
    clock.tick();

    assertThat(ram.getAddress().getValue(), equalTo(Byte.of(2)));
  }

  @Test
  public void shouldSetMemoryValue() {
    Clock clock = new Clock();

    ByteBus bus = new ByteBus("commonBus");
    Ram ram = new Ram(bus, clock, new Byte[256]);

    bus.put(Byte.of(2));
    ram.setAddress(true);
    clock.tick();
    ram.setAddress(false);

    bus.put(Byte.of(255));
    ram.setMemory(true);
    clock.tick();
    ram.setMemory(false);

    bus.put(Byte.of(0));
    ram.setMemoryEnable(true);
    clock.tick();

    assertThat(bus.getValue(), equalTo(Byte.of(255)));
  }

}