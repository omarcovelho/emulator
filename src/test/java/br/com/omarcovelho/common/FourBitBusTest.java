package br.com.omarcovelho.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FourBitBusTest {

    @Test
    public void shouldValidateMaxValue() {
        FourBitBus bus = new FourBitBus("bus");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> bus.put(Byte.of(255)));
        assertThat(ex.getMessage(), equalTo("this bus has only [4] bits and does not support [11111111], max value is [1111]"));
    }
}