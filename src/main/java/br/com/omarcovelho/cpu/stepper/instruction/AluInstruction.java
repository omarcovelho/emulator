package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class AluInstruction implements Instruction {

    @Override
    public boolean supports(Data instruction) {
        return (0b10000000 & instruction.toInt()) > 0;
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
            (ir) ->  {
                int regBAddress = ir.getValue().toInt() & 0b00000011;
                ComponentsRegistry.resolveRegister(regBAddress).setEnable(true);
                ComponentsRegistry.get(ComponentType.TMP).setSet(true);
            },
            (ir) ->  {
                int operationCode = (ir.getValue().toInt() & 0b01110000) >> 4;
                ComponentsRegistry.getAlu().setOperation(operationCode);
                int regAAddress = (ir.getValue().toInt() & 0b00001100) >> 2;
                ComponentsRegistry.resolveRegister(regAAddress).setEnable(true);
                ComponentsRegistry.get(ComponentType.ACC).setSet(true);

            },
            (ir) ->  {
                int regBAddress = ir.getValue().toInt() & 0b00000011;
                ComponentsRegistry.get(ComponentType.ACC).setEnable(true);
                ComponentsRegistry.resolveRegister(regBAddress).setSet(true);
            }
        );
    }
}
