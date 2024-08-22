package br.com.omarcovelho.common;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Clock {
    private List<Clockable> components = new ArrayList<>();

    public void tick() {
        components.forEach(Clockable::clkEnable);
        components.forEach(Clockable::clkSet);
    }

    public void register(Clockable component) {
        components.add(component);
    }
}

