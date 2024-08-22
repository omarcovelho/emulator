package br.com.omarcovelho;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AluTest {

    @Test
    public void shouldAddTwoRegisters() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b00001101));
        tmp.set();
        commonBus.put(Byte.of(0b00001010));
        a.set();

        alu.add();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(23)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldAddWithCarryIn() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(13));
        tmp.set();
        commonBus.put(Byte.of(10));
        a.set();

        alu.add();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(24)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldSetCarryOutWhenAdding() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(255));
        tmp.set();
        commonBus.put(Byte.of(255));
        a.set();

        alu.add();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(255)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldShiftRight() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(8));
        a.set();

        alu.shiftRight();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(4)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldShiftRightWithCarryIn() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(2));
        a.set();

        alu.shiftRight();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(129)));
    }

    @Test
    public void shouldShiftRightWithCarryOut() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(171));
        a.set();

        alu.shiftRight();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(85)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldShiftLeft() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(9));
        a.set();

        alu.shiftLeft();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(18)));
        assertFalse(alu.isCarryOut());
    }

    @Test
    public void shouldShiftLeftWithCarryOut() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);
        alu.setCarryIn(true);

        commonBus.put(Byte.of(0b11000001));
        a.set();

        alu.shiftLeft();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b10000011)));
        assertTrue(alu.isCarryOut());
    }

    @Test
    public void shouldNotRegister() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b10101001));
        a.set();

        alu.not();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b01010110)));
    }

    @Test
    public void shouldAndTwoRegisters() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b11000011));
        tmp.set();
        commonBus.put(Byte.of(0b10111001));
        a.set();

        alu.and();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b10000001)));
    }

    @Test
    public void shouldOrTwoRegisters() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b11000011));
        tmp.set();
        commonBus.put(Byte.of(0b10111001));
        a.set();

        alu.or();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b11111011)));
    }

    @Test
    public void shouldXorTwoRegisters() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b11000011));
        tmp.set();
        commonBus.put(Byte.of(0b10111001));
        a.set();

        alu.xor();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b01111010)));
    }

    @Test
    public void shouldCompareTwoRegisters() {
        Bus commonBus = new Bus();
        Bus aluBus = new Bus();
        Register a = new Register(commonBus);
        Register tmp = new Register(commonBus);
        Register acc = new Register(aluBus);
        Alu alu = new Alu(a, tmp, acc, aluBus);

        commonBus.put(Byte.of(0b10000000));
        tmp.set();
        commonBus.put(Byte.of(0b00001000));
        a.set();

        alu.compare();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b10001000)));
        assertFalse(alu.isALarger());
        assertFalse(alu.isEqual());

        commonBus.put(Byte.of(0b10000000));
        a.set();
        commonBus.put(Byte.of(0b00001000));
        tmp.set();

        alu.compare();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b10001000)));
        assertTrue(alu.isALarger());
        assertFalse(alu.isEqual());

        commonBus.put(Byte.of(0b10000000));
        a.set();
        commonBus.put(Byte.of(0b10000000));
        tmp.set();

        alu.compare();
        acc.set();

        assertThat(acc.getValue(), equalTo(Byte.of(0b00000000)));
        assertFalse(alu.isALarger());
        assertTrue(alu.isEqual());
    }
}