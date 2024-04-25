package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/cidadao/")
public class CidadaoRestController {

    @Autowired
    private TipoService tipoService;
    @GetMapping(value = "teste-conexao")
    public  String testeConexao()
    {
        return "conectado";
    }
    // demais apis

    @GetMapping(value = "/get-all-tipos")
    public ResponseEntity<Object> buscarTodosTipos()
    {
        return new ResponseEntity<>(tipoService.getAll(), HttpStatus.OK);
    }

}
