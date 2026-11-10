package com.example.viacep.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class CepExceptionHandler {
    //trata o erro de cep  inválido
    @ExceptionHandler(CepInvalidoException.class)
    ProblemDetail invalido(CepInvalidoException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    //trata o erro de cep não encontrado
    @ExceptionHandler(CepNaoEncontradoException.class)
    ProblemDetail naoEncontrado(CepNaoEncontradoException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    //trata de parametros inadequados pelo usuário, ex.:um cep fora do padrão esperado
    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail parametrosInvalido(ConstraintViolationException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    //trata de erros com a comunicação com API
    @ExceptionHandler(ResourceAccessException.class)
    ProblemDetail apiIndisponivel(ResourceAccessException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, "Serviço da API indisponível");
    }

}
