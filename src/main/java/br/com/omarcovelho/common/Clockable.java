package br.com.omarcovelho.common;

public interface Clockable {
    default void clkEnable() {};
    default void clkSet() {};
    default void preClock() {};
    default void clkFinish() {};
    default void subscribe(Clock clock) {
        clock.register(this);
    };
}
