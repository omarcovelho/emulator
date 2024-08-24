package br.com.omarcovelho.common;

import br.com.omarcovelho.cpu.alu.Alu;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComponentsRegistry {
    private final static Map<ComponentType, ControlledComponent> allComponents = new HashMap<>();
    private final static List<ByteRegister> registerDecoder = new ArrayList<>();

    private static Alu alu;
    public static void put(Map<ComponentType, ControlledComponent> components) {
        allComponents.putAll(components);
    }

    public static ControlledComponent get(ComponentType component) {
        return Optional.ofNullable(allComponents.get(component))
                .orElseThrow(() -> new IllegalArgumentException("component " + component.name() + " not found."));
    }

    public static ByteRegister resolveRegister(int address) {
        return registerDecoder.get(address);
    }

    public static void setAlu(Alu alu) {
        ComponentsRegistry.alu = alu;
    }

    public static Alu getAlu() {
        return alu;
    }

    public static void setRegisterDecoder(List<ByteRegister> registers) {
        registerDecoder.clear();
        registerDecoder.addAll(registers);
    }

    public static Collection<ControlledComponent> getAll() {
        return allComponents.values();
    }

    public static Iterable<ControlledComponent> getRegisters() {
        return new ArrayList<>(registerDecoder);
    }
}
