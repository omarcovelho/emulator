package br.com.omarcovelho;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ControlledComponent {
    private boolean enable;
    private boolean set;
}
