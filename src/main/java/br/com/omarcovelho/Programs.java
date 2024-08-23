package br.com.omarcovelho;

import br.com.omarcovelho.common.Byte;

public class Programs {
    public static Byte[] jumpLoop() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b00010000);//data loaded to r0 address to read from
        values[2] = Byte.of(0b00100001);//load data to r1
        values[3] = Byte.of(0b11111111);//data loaded to r1 address to write to

        values[4] = Byte.of(0b00010001);//store to r1 what is in r0 address
        values[5] = Byte.of(0b00000010);//load from r0 address on r2

        values[6] = Byte.of(0b00100010);//load data to r2
        values[7] = Byte.of(0b00000000);//data loaded to r1 address to write to
        values[8] = Byte.of(0b01000000);//jump to address on next instruction
        values[9] = Byte.of(0b00000000);//address to jump to

        values[16] = Byte.of(0b00000000);//should be stored on r1

        return values;
    }

    public static Byte[] addRegisters() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b00000010);//data loaded to r0 address to read from
        values[2] = Byte.of(0b00100001);//load data to r1
        values[3] = Byte.of(0b00000001);//data loaded to r1 address to write to

        values[4] = Byte.of(0b10000001);//sum r0 to r1 and store on r1

        return values;
    }

    public static Byte[] shiftRightRegisters() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b00000010);//data loaded to r0 address to read from
        values[2] = Byte.of(0b10100001);//shift right r0 and store on r1

        return values;
    }

    public static Byte[] shiftLeftRegisters() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b00000010);//data loaded to r0 address to read from
        values[2] = Byte.of(0b10010001);//shift left r0 and store on r1

        return values;
    }

    public static Byte[] notRegisters() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b11110000);//data loaded to r0 address to read from
        values[2] = Byte.of(0b10110001);//not r0 and store on r1

        return values;
    }

    public static Byte[] andRegisters() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b11110011);//data loaded to r0 address to read from
        values[2] = Byte.of(0b00100001);//load data to r1
        values[3] = Byte.of(0b00110000);//data loaded to r1 address to read from
        values[4] = Byte.of(0b11000001);//not r0 and store on r1

        return values;
    }

    public static Byte[] orRegisters() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b11110011);//data loaded to r0 address to read from
        values[2] = Byte.of(0b00100001);//load data to r1
        values[3] = Byte.of(0b00110000);//data loaded to r1 address to read from
        values[4] = Byte.of(0b11010001);//not r0 and store on r1

        return values;
    }

    public static Byte[] xorRegisters() {
        Byte[] values = new Byte[256];

        values[0] = Byte.of(0b00100000);//load data to r0
        values[1] = Byte.of(0b11110011);//data loaded to r0 address to read from
        values[2] = Byte.of(0b00100001);//load data to r1
        values[3] = Byte.of(0b00110000);//data loaded to r1 address to read from
        values[4] = Byte.of(0b11100001);//not r0 and store on r1

        return values;
    }
}
