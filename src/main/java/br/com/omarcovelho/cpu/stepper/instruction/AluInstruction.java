package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;
import br.com.omarcovelho.cpu.alu.AluOperationFactory;

import java.util.Arrays;
import java.util.List;

public class AluInstruction implements Instruction {

    @Override
    public boolean supports(Data instruction) {
        return 0b1 == (instruction.toInt() >> 7);
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
            (ir, componentsRegistry) ->  {
                int regBAddress = ir.getValue().toInt() & 0b00000011;
                componentsRegistry.resolveRegister(regBAddress).setEnable(true);
                componentsRegistry.get(ComponentType.TMP).setSet(true);
            },
            (ir, componentsRegistry) ->  {
                int operationCode = (ir.getValue().toInt() & 0b01110000) >> 4;
                componentsRegistry.getAlu().setOperation(operationCode);
                System.out.println("Execution ALU operation: " + componentsRegistry.getAlu().getOperation().getClass().getSimpleName());
                int regAAddress = (ir.getValue().toInt() & 0b00001100) >> 2;
                componentsRegistry.resolveRegister(regAAddress).setEnable(true);
                componentsRegistry.get(ComponentType.ACC).setSet(true);
                componentsRegistry.getAlu().getFlagsRegister().setSet(true);
            },
            (ir, componentsRegistry) ->  {
                int operationCode = (ir.getValue().toInt() & 0b01110000) >> 4;
                if(operationCode != AluOperationFactory.CMP) {
                    int regBAddress = ir.getValue().toInt() & 0b00000011;
                    componentsRegistry.get(ComponentType.ACC).setEnable(true);
                    componentsRegistry.resolveRegister(regBAddress).setSet(true);
                }
            }
        );
    }
}
