package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.List;

public interface Instruction {

    boolean supports(Data instruction);

    List<InstructionStep> getSteps(Data instruction);
}
