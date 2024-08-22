package br.com.omarcovelho.cpu;

@FunctionalInterface
public interface InstructionStep {

    void execute();
}
