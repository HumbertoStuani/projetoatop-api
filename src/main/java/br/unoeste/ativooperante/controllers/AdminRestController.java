package br.unoeste.ativooperante.controllers;


import br.unoeste.ativooperante.db.entities.Orgao;
import br.unoeste.ativooperante.db.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apis/admin/")
public class AdminRestController {

    @Autowired
    OrgaoRepository orgaorepo;

    @GetMapping(value = "teste-admin")
    public String testeadm() {
        return "conectado";
    }
    // demais api


    @GetMapping("/delete-orgao")
    public void excluirOrgao(@RequestParam(value = "id") Long id) {
        orgaorepo.deleteById(id);
    }

    @PostMapping("/add-orgao")
    public ResponseEntity<Object> addOrgao (@RequestBody Orgao orgao) {
        return new ResponseEntity<>(orgaorepo.save(orgao), HttpStatus.OK);
    }

    @GetMapping("/get-orgao")
    public ResponseEntity<Object> buscarUm(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(orgaorepo.findById(id).
                orElse(new Orgao()), HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-orgaos")
    public ResponseEntity<Object> buscarTodos()
    {
        return new ResponseEntity<>(orgaorepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/get-denuncia")
    public ResponseEntity<Object> buscaUmaDenuncia(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(orgaorepo.findById(id).
                orElse(new Orgao()), HttpStatus.OK);
    }

}
