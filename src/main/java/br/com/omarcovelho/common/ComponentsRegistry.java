package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.Alu;

import java.util.*;

public class ComponentsRegistry {
    private final Map<ComponentType, ControlledComponent> allComponents = new HashMap<>();
    private final List<ByteRegister> registerDecoder = new ArrayList<>();
    private static Alu alu;
    public void put(Map<ComponentType, ControlledComponent> components) {
        allComponents.putAll(components);
    }

    public ControlledComponent get(ComponentType component) {
        return Optional.ofNullable(allComponents.get(component))
                .orElseThrow(() -> new IllegalArgumentException("component " + component.name() + " not found."));
    }

    public ByteRegister resolveRegister(int address) {
        return registerDecoder.get(address);
    }

    public void setAlu(Alu alu) {
        ComponentsRegistry.alu = alu;
    }

    public Alu getAlu() {
        return alu;
    }

    public void setRegisterDecoder(List<ByteRegister> registers) {
        registerDecoder.clear();
        registerDecoder.addAll(registers);
    }

    public Collection<ControlledComponent> getAll() {
        return allComponents.values();
    }

    public Iterable<ControlledComponent> getRegisters() {
        return new ArrayList<>(registerDecoder);
    }
}
