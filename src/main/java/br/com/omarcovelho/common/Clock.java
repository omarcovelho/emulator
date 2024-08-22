package br.com.omarcovelho.common;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Clock {
    private List<Clockable> clockableComponents = new ArrayList<>();

    public void tick() {
        clockableComponents.forEach(Clockable::preClock);
        clockableComponents.forEach(Clockable::clkEnable);
        clockableComponents.forEach(Clockable::clkSet);
        clockableComponents.forEach(Clockable::clkFinish);
    }

    public void register(Clockable component) {
        clockableComponents.add(component);
    }
}

