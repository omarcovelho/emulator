package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.ByteRegister;

@FunctionalInterface
public interface InstructionStep {

    void execute(ByteRegister ir);
}
