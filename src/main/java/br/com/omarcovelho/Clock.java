package br.com.omarcovelho;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Clock {
    private List<Clockeable> components = new ArrayList<>();

    public void tick() {
        components.forEach(Clockeable::enable);
        components.forEach(Clockeable::set);
    }

    public void register(Clockeable component) {
        components.add(component);
    }
}

