package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.AbstractRegister;

public interface RegisterSubscriber {

    void onRegisterChange(AbstractRegister register);
}
