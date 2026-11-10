package com.example.viacep.controller;

import com.example.viacep.dto.CepResponse;
import com.example.viacep.service.CepService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }
    @GetMapping("/{id}")
    public CepResponse buscaPorCep(@PathVariable String id){
        return cepService.getCep(id);
    }

    @GetMapping
    public List<CepResponse>buscarPorEndereco(@RequestParam @Size(min=2, max=2) String uf,
                                              @RequestParam @NotBlank String cidade,
                                              @RequestParam @Size(min=3)String logradouro){
        return cepService.getByEndereco(uf,cidade,logradouro);
    }
}
