package br.com.omarcovelho.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ByteBus extends AbstractBus {

  public ByteBus(String id) {
    super(Byte.of(0), id, 8);
  }
}
