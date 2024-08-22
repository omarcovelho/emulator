package br.com.omarcovelho.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ControlledComponent {
    private boolean enable;
    private boolean set;

    public void clearFlags() {
        this.enable = false;
        this.set = false;
    }
}
