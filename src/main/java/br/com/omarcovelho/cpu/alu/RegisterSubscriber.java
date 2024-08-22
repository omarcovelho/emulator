package br.com.omarcovelho.cpu.alu;

import br.com.omarcovelho.common.Register;

public interface RegisterSubscriber {

    void onRegisterChange(Register register);
}
