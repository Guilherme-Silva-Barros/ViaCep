package com.example.viacep.exception;

public class CepInvalidoException extends RuntimeException {
    public CepInvalidoException(String cep) {
        super("CEP inválido: "+cep);
    }
}
