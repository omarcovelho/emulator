package br.com.omarcovelho;

import lombok.Getter;

@Getter
public class Ram extends ControlledComponent implements Clockable {
  private final RamAddress address;
  private final RamMemory memory;

  public Ram(Bus bus, Clock clock) {
    this.address = new RamAddress(bus);
    this.memory = new RamMemory(bus);
    this.subscribe(clock);
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

  @Override
  public void subscribe(Clock clock) {
    clock.register(this);
  }
}
