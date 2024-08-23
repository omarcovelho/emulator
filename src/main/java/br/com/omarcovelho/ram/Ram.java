package br.com.omarcovelho.ram;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class Ram extends ControlledComponent implements Clockable {
  private final RamAddress address;
  private final RamMemory memory;

  public Ram(Bus bus, Clock clock, Byte[] program) {
    this.address = new RamAddress(bus);
    this.memory = new RamMemory(bus, program);
    this.subscribe(clock);

    ComponentsRegistry.put(Map.ofEntries(
        Map.entry(ComponentType.MAR, address),
        Map.entry(ComponentType.RAM, memory)
    ));
  }

  public void setAddress(boolean b) {
    this.address.setSet(b);
  }

  public void setMemory(boolean b) {
    this.memory.setSet(b);
  }

  public void setMemoryEnable(boolean b) {
    this.memory.setEnable(b);
  }

  @Override
  public void clkEnable() {
    if(this.getMemory().isEnable()) {
      this.getMemory().enable(this.getAddress());
    }
  }

  @Override
  public void clkSet() {
    if(this.getAddress().isSet()) {
      this.getAddress().set();
    }
    if(this.getMemory().isSet()) {
      this.getMemory().set(this.address);
    }
  }

    public void printState() {
      System.out.println("Current Memory Address: " + this.address);
//      System.out.println("Memory Values: " + this.memory);
    }
}
