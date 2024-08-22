package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.BusSubscriber;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Bus {
  private Byte value = new Byte();
  private final String id;
  private final List<BusSubscriber> watchers = new ArrayList<>();

  public Byte getValue() {
    return Byte.of(value);
  }

  public void put(Byte value) {
    this.value = Byte.of(value);
    watchers.forEach(BusSubscriber::onBusChange);
  }
  public void subscribe(BusSubscriber busSubscriber) {
    this.watchers.add(busSubscriber);
  }
}
