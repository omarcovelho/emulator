package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.ByteRegister;
import br.com.omarcovelho.common.ComponentsRegistry;

@FunctionalInterface
public interface InstructionStep {

    void execute(ByteRegister ir, ComponentsRegistry componentsRegistry);
}
