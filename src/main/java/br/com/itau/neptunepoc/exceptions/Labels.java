package br.com.itau.neptunepoc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Labels {
    CODE("code"),
    MESSAGE("message");

    private String sufix;
}
