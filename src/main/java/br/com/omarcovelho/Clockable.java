package br.com.omarcovelho;

public interface Clockable {
    void clkEnable();
    void clkSet();
    default void clk() {};
    void subscribe(Clock clock);
}
