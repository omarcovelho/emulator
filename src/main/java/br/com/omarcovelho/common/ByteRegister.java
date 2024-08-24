package br.com.omarcovelho.common;

public class ByteRegister extends AbstractRegister implements Clockable {

  public ByteRegister(ByteBus bus, Clock clock, String id) {
    super(Byte.of(0), bus, id, clock);
  }
}
