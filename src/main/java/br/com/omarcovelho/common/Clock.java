package br.com.omarcovelho.common;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Clock {
    private List<Clockable> clockableComponents = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public void tick() {
        clockableComponents.forEach(Clockable::preClock);
        clockableComponents.forEach(Clockable::clkEnable);
        clockableComponents.forEach(Clockable::clkSet);
        clockableComponents.forEach(Clockable::clkFinish);
        counter.incrementAndGet();
    }

    public void register(Clockable component) {
        clockableComponents.add(component);
    }

    public int getTicks() {
        return counter.get();
    }
}

