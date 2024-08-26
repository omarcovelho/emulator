package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.*;
import br.com.omarcovelho.cpu.alu.Alu;
import br.com.omarcovelho.cpu.alu.AluOperationFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AluTest {

    @Test
    public void shouldAddTwoRegisters() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");

        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b00001101));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b00001010));

        alu.setOperation(AluOperationFactory.ADD);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b00010111)));
        assertFalse(alu.getFlagsRegister().isCarryOut());
    }

    @Test
    public void shouldAddTwoRegistersWithBus1() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");

        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b00000001));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b00001010));
        alu.setBus1(true);

        alu.setOperation(AluOperationFactory.ADD);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b00001011)));
        assertFalse(alu.getFlagsRegister().isCarryOut());
    }

    private static void setOutputRegisters(Clock clock, Alu register) {
        register.getAcc().setSet(true);
        register.getFlagsRegister().setSet(true);
        clock.tick();
        register.getAcc().setSet(false);
        register.getFlagsRegister().setSet(false);
    }

    private static void setValueOnRegister(Clock clock, ByteRegister register) {
        register.setSet(true);
        clock.tick();
        register.setSet(false);
    }

    @Test
    public void shouldAddWithCarryIn() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());
        alu.getFlagsRegister().getBus().put(Nibble.of(0b1000));
        clock.tick();

        commonBus.put(Byte.of(13));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(10));

        alu.setOperation(AluOperationFactory.ADD);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(24)));
        assertFalse(alu.getFlagsRegister().isCarryOut());
    }

    @Test
    public void shouldSetCarryOutWhenAdding() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());
        alu.getFlagsRegister().getBus().put(Nibble.of(0b1000));
        clock.tick();

        commonBus.put(Byte.of(255));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(255));

        alu.setOperation(AluOperationFactory.ADD);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(255)));
        assertTrue(alu.getFlagsRegister().isCarryOut());
    }

    @Test
    public void shouldShiftRight() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(8));

        alu.setOperation(AluOperationFactory.SHR);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(4)));
        assertFalse(alu.getFlagsRegister().isCarryOut());
    }

    @Test
    public void shouldShiftRightWithCarryIn() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());
        alu.getFlagsRegister().getBus().put(Nibble.of(0b1000));
        clock.tick();

        commonBus.put(Byte.of(2));

        alu.setOperation(AluOperationFactory.SHR);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(129)));
    }

    @Test
    public void shouldShiftRightWithCarryOut() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b10101011));

        alu.setOperation(AluOperationFactory.SHR);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b01010101)));
        assertTrue(alu.getFlagsRegister().isCarryOut());
    }

    @Test
    public void shouldShiftLeft() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b00001001));

        alu.setOperation(AluOperationFactory.SHL);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b00010010)));
        assertFalse(alu.getFlagsRegister().isCarryOut());
    }

    @Test
    public void shouldShiftLeftWithCarryOut() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());
        alu.getFlagsRegister().getBus().put(Nibble.of(0b1000));
        clock.tick();

        commonBus.put(Byte.of(0b11000001));

        alu.setOperation(AluOperationFactory.SHL);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10000011)));
        assertTrue(alu.getFlagsRegister().isCarryOut());
    }

    @Test
    public void shouldNotRegister() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b10101001));

        alu.setOperation(AluOperationFactory.NOT);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b01010110)));
    }

    @Test
    public void shouldAndTwoRegisters() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10111001));

        alu.setOperation(AluOperationFactory.AND);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10000001)));
    }

    @Test
    public void shouldOrTwoRegisters() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10111001));

        alu.setOperation(AluOperationFactory.OR);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b11111011)));
    }

    @Test
    public void shouldXorTwoRegisters() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10111001));

        alu.setOperation(AluOperationFactory.XOR);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b01111010)));
    }

    @Test
    public void shouldCompareTwoRegisters() {
        Clock clock = new Clock();
        ByteBus commonBus = new ByteBus("commonBus");
        Alu alu = new Alu(clock, commonBus, new ComponentsRegistry());

        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b00001000));


        alu.setOperation(AluOperationFactory.CMP);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10001000)));
        assertFalse(alu.getFlagsRegister().isALarger());
        assertFalse(alu.getFlagsRegister().isEqual());

        commonBus.put(Byte.of(0b00001000));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10000000));

        alu.setOperation(AluOperationFactory.CMP);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10001000)));
        assertTrue(alu.getFlagsRegister().isALarger());
        assertFalse(alu.getFlagsRegister().isEqual());

        commonBus.put(Byte.of(0b10000000));
        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, alu.getTmp());

        alu.setOperation(AluOperationFactory.CMP);
        setOutputRegisters(clock, alu);

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b00000000)));
        assertFalse(alu.getFlagsRegister().isALarger());
        assertTrue(alu.getFlagsRegister().isEqual());
    }
}