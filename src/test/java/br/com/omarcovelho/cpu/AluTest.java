package br.com.omarcovelho.cpu;

import br.com.omarcovelho.common.Bus;
import br.com.omarcovelho.common.Byte;
import br.com.omarcovelho.common.Clock;
import br.com.omarcovelho.common.Register;
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
        Bus commonBus = new Bus("commonBus");

        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(0b00001101));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b00001010));

        alu.setOperation(AluOperationFactory.ADD);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b00010111)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldAddTwoRegistersWithBus1() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");

        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(0b00000001));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b00001010));
        alu.setBus1(true);

        alu.setOperation(AluOperationFactory.ADD);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b00000010)));
        assertFalse(alu.isCarryOut());
    }

    private static void setAcc(Clock clock, Register register) {
        register.setSet(true);
        clock.tick();
        register.setSet(false);
    }

    private static void setValueOnRegister(Clock clock, Register tmp) {
        setAcc(clock, tmp);
    }

    @Test
    public void shouldAddWithCarryIn() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(13));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(10));

        alu.setOperation(AluOperationFactory.ADD);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(24)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldSetCarryOutWhenAdding() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(255));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(255));

        alu.setOperation(AluOperationFactory.ADD);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(255)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldShiftRight() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(8));

        alu.setOperation(AluOperationFactory.SHR);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(4)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldShiftRightWithCarryIn() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(2));

        alu.setOperation(AluOperationFactory.SHR);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(129)));
    }

    @Test
    public void shouldShiftRightWithCarryOut() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(171));

        alu.setOperation(AluOperationFactory.SHR);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(85)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldShiftLeft() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(9));

        alu.setOperation(AluOperationFactory.SHL);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(18)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldShiftLeftWithCarryOut() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(0b11000001));

        alu.setOperation(AluOperationFactory.SHL);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10000011)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldNotRegister() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(0b10101001));

        alu.setOperation(AluOperationFactory.NOT);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b01010110)));
    }

    @Test
    public void shouldAndTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10111001));

        alu.setOperation(AluOperationFactory.AND);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10000001)));
    }

    @Test
    public void shouldOrTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10111001));

        alu.setOperation(AluOperationFactory.OR);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b11111011)));
    }

    @Test
    public void shouldXorTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10111001));

        alu.setOperation(AluOperationFactory.XOR);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b01111010)));
    }

    @Test
    public void shouldCompareTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus("commonBus");
        Alu alu = new Alu(clock, commonBus);

        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b00001000));


        alu.setOperation(AluOperationFactory.CMP);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10001000)));
        assertFalse(alu.isALarger());
        assertFalse(alu.isEqual());

        commonBus.put(Byte.of(0b00001000));
        setValueOnRegister(clock, alu.getTmp());
        commonBus.put(Byte.of(0b10000000));

        alu.setOperation(AluOperationFactory.CMP);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b10001000)));
        assertTrue(alu.isALarger());
        assertFalse(alu.isEqual());

        commonBus.put(Byte.of(0b10000000));
        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, alu.getTmp());

        alu.setOperation(AluOperationFactory.CMP);
        setAcc(clock, alu.getAcc());

        assertThat(alu.getAcc().getValue(), equalTo(Byte.of(0b00000000)));
        assertFalse(alu.isALarger());
        assertTrue(alu.isEqual());
    }
}