package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.List;

public interface Instruction {

    boolean supports(Byte instruction);

    List<InstructionStep> getSteps(Byte instruction);
}
