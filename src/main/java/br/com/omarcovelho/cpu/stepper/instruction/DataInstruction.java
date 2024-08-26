package br.com.omarcovelho.cpu.stepper.instruction;

import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.Data;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class DataInstruction implements Instruction {

    @Override
    public boolean supports(Data instruction) {
        return 0b0010 == (instruction.toInt() >> 4);
    }

    @Override
    public List<InstructionStep> getSteps(Data instruction) {
        return Arrays.asList(
            (ir, componentsRegistry) ->  {
                componentsRegistry.getAlu().setBus1(true);
                componentsRegistry.get(ComponentType.IAR).setEnable(true);
                componentsRegistry.get(ComponentType.MAR).setSet(true);
                componentsRegistry.get(ComponentType.ACC).setSet(true);
            },
            (ir, componentsRegistry) ->  {
                int registerAddress = ir.getValue().toInt() & 0b00000011;
                componentsRegistry.get(ComponentType.RAM).setEnable(true);
                componentsRegistry.resolveRegister(registerAddress).setSet(true);
            },
            (ir, componentsRegistry) ->  {
                componentsRegistry.get(ComponentType.ACC).setEnable(true);
                componentsRegistry.get(ComponentType.IAR).setSet(true);
            }
        );
    }
}
