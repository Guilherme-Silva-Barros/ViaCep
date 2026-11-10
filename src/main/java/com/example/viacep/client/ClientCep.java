package com.example.viacep.client;

import com.example.viacep.dto.CepResponse;
import com.example.viacep.exception.CepInvalidoException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class ClientCep {

    private final RestClient restClient;
    public ClientCep(RestClient restClient) {
        this.restClient = restClient;
    }
    public CepResponse listarPorCep(String cep){
        return restClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new CepInvalidoException(cep);
                })
                .body(CepResponse.class);
    }
    public List<CepResponse>buscaPorEndereco(String uf, String localidade, String logradouro){
        return restClient.get()
                .uri("/{uf}/{localidade}/{logradouro}/json", uf, localidade, logradouro)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CepResponse>>(){});
    }
}
