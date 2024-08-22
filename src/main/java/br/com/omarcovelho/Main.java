package br.com.omarcovelho;

public class Main {

  public static void main(String[] args) {
    Clock clock = new Clock();
    Bus bus = new Bus();
    Computer computer = new Computer(clock, bus);
    clock.tick();
  }
}