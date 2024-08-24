package br.com.omarcovelho;

import br.com.omarcovelho.common.Clock;

public class Main {

  public static void main(String[] args) {
    Clock clock = new Clock();
    Computer computer = new Computer(clock, Programs.jumpIfCarry());


    while(true) {
      System.out.println("\nTicks:" + clock.getTicks());
      computer.printState();
      clock.tick();
    }
  }
}