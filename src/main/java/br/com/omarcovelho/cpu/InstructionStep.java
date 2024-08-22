package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.Register;

@FunctionalInterface
public interface InstructionStep {

    void execute(Register ir);
}
