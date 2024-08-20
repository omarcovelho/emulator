package br.com.omarcovelho;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Ram {
  private final RamAddress address;
  private final RamMemory memory;

  public void setAddress() {
    this.address.set();
  }

  public void setMemory() {
    this.memory.set(address);
  }

  public void enableMemory() {
    this.memory.enable(this.address);
  }
}
