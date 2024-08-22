package br.com.omarcovelho;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AluTest {

    @Test
    public void shouldAddTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);

        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b00001101));
        setValueOnRegister(clock, tmp);
        commonBus.put(Byte.of(0b00001010));
        setValueOnRegister(clock, a);

        alu.add();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(23)));
        assertFalse(alu.isCarryOut());
    }

    private static void setAcc(Clock clock, Register acc) {
        acc.setSet(true);
        clock.tick();
        acc.setSet(false);
    }

    private static void setValueOnRegister(Clock clock, Register tmp) {
        setAcc(clock, tmp);
    }

    @Test
    public void shouldAddWithCarryIn() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(13));
        setValueOnRegister(clock, tmp);
        commonBus.put(Byte.of(10));
        setValueOnRegister(clock, a);

        alu.add();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(24)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldSetCarryOutWhenAdding() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(255));
        setValueOnRegister(clock, tmp);
        commonBus.put(Byte.of(255));
        setValueOnRegister(clock, a);

        alu.add();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(255)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldShiftRight() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(8));
        setValueOnRegister(clock, a);

        alu.shiftRight();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(4)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldShiftRightWithCarryIn() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(2));
        setValueOnRegister(clock, a);

        alu.shiftRight();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(129)));
    }

    @Test
    public void shouldShiftRightWithCarryOut() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(171));
        setValueOnRegister(clock, a);

        alu.shiftRight();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(85)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldShiftLeft() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(9));
        setValueOnRegister(clock, a);

        alu.shiftLeft();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(18)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldShiftLeftWithCarryOut() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(0b11000001));
        setValueOnRegister(clock, a);

        alu.shiftLeft();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b10000011)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldNotRegister() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b10101001));
        setValueOnRegister(clock, a);

        alu.not();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b01010110)));
    }

    @Test
    public void shouldAndTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, tmp);
        commonBus.put(Byte.of(0b10111001));
        setValueOnRegister(clock, a);

        alu.and();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b10000001)));
    }

    @Test
    public void shouldOrTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, tmp);
        commonBus.put(Byte.of(0b10111001));
        setValueOnRegister(clock, a);

        alu.or();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b11111011)));
    }

    @Test
    public void shouldXorTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b11000011));
        setValueOnRegister(clock, tmp);
        commonBus.put(Byte.of(0b10111001));
        setValueOnRegister(clock, a);

        alu.xor();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b01111010)));
    }

    @Test
    public void shouldCompareTwoRegisters() {
        Clock clock = new Clock();
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus, clock);
        Register tmp = new Register(commonBus, clock);
        Register acc = new Register(aluBus, clock);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, tmp);
        commonBus.put(Byte.of(0b00001000));
        setValueOnRegister(clock, a);


        alu.compare();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b10001000)));
        assertFalse(alu.isALarger());
        assertFalse(alu.isEqual());

        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, a);
        commonBus.put(Byte.of(0b00001000));
        setValueOnRegister(clock, tmp);

        alu.compare();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b10001000)));
        assertTrue(alu.isALarger());
        assertFalse(alu.isEqual());

        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, a);
        commonBus.put(Byte.of(0b10000000));
        setValueOnRegister(clock, tmp);

        alu.compare();
        setAcc(clock, acc);

        assertThat(acc.getValue(), equalTo(Byte.of(0b00000000)));
        assertFalse(alu.isALarger());
        assertTrue(alu.isEqual());
    }
}