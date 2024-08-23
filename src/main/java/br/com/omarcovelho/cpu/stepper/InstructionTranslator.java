package br.com.omarcovelho.cpu.stepper;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.ComponentType;
import br.com.omarcovelho.common.ComponentsRegistry;
import br.com.omarcovelho.cpu.InstructionStep;

import java.util.Arrays;
import java.util.List;

public class InstructionTranslator {

    private final InstructionStep[][] instructionStepsMap;

    public InstructionTranslator() {
        this.instructionStepsMap = new InstructionStep[16][];
        populateInstructions();
    }

    private void populateInstructions() {//TODO: extract steps to class
        this.instructionStepsMap[0b0010] = new InstructionStep[]{ //data instruction
            (ir) ->  {
                ComponentsRegistry.getAlu().setBus1(true);
                ComponentsRegistry.get(ComponentType.IAR).setEnable(true);
                ComponentsRegistry.get(ComponentType.MAR).setSet(true);
                ComponentsRegistry.get(ComponentType.ACC).setSet(true);
            },
            (ir) ->  {
                int registerAddress = ir.getValue().toInt() & 0b00001111;
                ComponentsRegistry.get(ComponentType.RAM).setEnable(true);
                ComponentsRegistry.resolveRegister(registerAddress).setSet(true);
            },
            (ir) ->  {
                ComponentsRegistry.get(ComponentType.ACC).setEnable(true);
                ComponentsRegistry.get(ComponentType.IAR).setSet(true);
            }
        };
        this.instructionStepsMap[0b0000] = new InstructionStep[] { //load instruction
                (ir) -> {
                    int addressRegister = ir.getValue().toInt() & 0b00001100;
                    ComponentsRegistry.resolveRegister(addressRegister).setEnable(true);
                    ComponentsRegistry.get(ComponentType.MAR).setSet(true);
                },
                (ir) -> {
                    int targetRegister = ir.getValue().toInt() & 0b000000011;
                    ComponentsRegistry.get(ComponentType.RAM).setEnable(true);
                    ComponentsRegistry.resolveRegister(targetRegister).setSet(true);
                }
        };
        this.instructionStepsMap[0b0001] = new InstructionStep[] { //store instruction
                (ir) -> {
                    int addressRegister = ir.getValue().toInt() & 0b00001100;
                    ComponentsRegistry.resolveRegister(addressRegister).setEnable(true);
                    ComponentsRegistry.get(ComponentType.MAR).setSet(true);
                },
                (ir) -> {
                    int targetRegister = ir.getValue().toInt() & 0b000000011;
                    ComponentsRegistry.resolveRegister(targetRegister).setEnable(true);
                    ComponentsRegistry.get(ComponentType.RAM).setSet(true);
                }
        };
    }


    public List<InstructionStep> translate(Byte instruction) {
        InstructionStep[] steps = instructionStepsMap[(instruction.toInt() >> 4)];
        return Arrays.asList(steps);
    }
}
