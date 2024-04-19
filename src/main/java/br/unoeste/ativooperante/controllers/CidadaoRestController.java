package br.unoeste.ativooperante.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "apis/cidadao/")
public class CidadaoRestController {
    @GetMapping(value = "teste-conexao")
    public  String testeConexao()
    {
        return "conectado";
    }
    // demais apis
}
