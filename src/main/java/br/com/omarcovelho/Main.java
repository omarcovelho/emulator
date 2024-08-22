package br.com.omarcovelho;

import br.com.omarcovelho.common.Bus;
import br.com.omarcovelho.common.Clock;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Clock clock = new Clock();
    Bus bus = new Bus("commonBus");

    Scanner scanner = new Scanner(System.in);
    Computer computer = new Computer(clock, bus);

    while(true) {
      computer.printState();
//      System.out.println("Enter to tick...");
//      scanner.nextLine();
      clock.tick();
    }
  }
}