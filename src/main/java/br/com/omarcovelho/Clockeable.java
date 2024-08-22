package br.com.omarcovelho;

public interface Clockeable {
    void enable();
    void set();

    void subscribe(Clock clock);
}
