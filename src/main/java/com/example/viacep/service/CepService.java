package com.example.viacep.service;


import com.example.viacep.client.ClientCep;
import com.example.viacep.dto.CepResponse;
import com.example.viacep.exception.CepInvalidoException;
import com.example.viacep.exception.CepNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CepService {

    private final ClientCep clientCep;
    public CepService(ClientCep clientCep) {
        this.clientCep = clientCep;
    }

    public CepResponse getCep(String cepBruto){
        String cep = cepBruto.replaceAll("\\D", "");
        if(cep.length()!=8){
            throw new CepInvalidoException(cepBruto);
        }
        CepResponse resposta = clientCep.listarPorCep(cep);
        if(resposta==null|| Boolean.TRUE.equals(resposta.erro())){
            throw new CepNaoEncontradoException(cep);
        }
        return resposta;
    }

    public List<CepResponse>getByEndereco(String uf, String localidade, String logradouro){
        return clientCep.buscaPorEndereco(uf, localidade, logradouro);
    }

}
