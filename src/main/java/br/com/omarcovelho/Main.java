package br.com.omarcovelho;

import br.com.omarcovelho.common.Clock;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Clock clock = new Clock();
    Scanner scanner = new Scanner(System.in);
    Computer computer = new Computer(clock);


    while(true) {
      System.out.println("");
      System.out.println("Ticks:" + clock.getTicks());
      computer.printState();
//      System.out.println("Enter to tick...");
//      scanner.nextLine();
      clock.tick();
    }
  }
}