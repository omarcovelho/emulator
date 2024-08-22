package br.com.omarcovelho.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComponentsRegistry {
    private final static Map<ComponentType, ControlledComponent> allComponents = new HashMap<>();

    public static void put(Map<ComponentType, ControlledComponent> components) {
        allComponents.putAll(components);
    }

    public static Object get(ComponentType component) {
        return Optional.ofNullable(allComponents.get(component))
                .orElseThrow(() -> new IllegalArgumentException("component " + component.name() + " not found."));
    }

    public static Collection<ControlledComponent> getAll() {
        return allComponents.values();
    }
}
