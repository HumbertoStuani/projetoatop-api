package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.services.OrgaoService;
import br.unoeste.ativooperante.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "api/cidadao/")
public class CidadaoRestController {

    @Autowired
    private TipoService tipoService;
    @Autowired
    private OrgaoService orgaoService;

    @GetMapping(value = "teste-conexao")
    public  String testeConexao()
    {
        return "conectado";
    }

    @GetMapping(value = "/tipos")
    public ResponseEntity<Object> buscarTodosTipos()
    {
        return new ResponseEntity<>(tipoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/orgaos")
    public ResponseEntity<Object> buscarTodosOrgaos() {
        return new ResponseEntity<>(orgaoService.getAll(), HttpStatus.OK);
    }
}
