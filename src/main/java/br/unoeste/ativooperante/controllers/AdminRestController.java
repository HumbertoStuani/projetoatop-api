package br.unoeste.ativooperante.controllers;


import br.unoeste.ativooperante.db.entities.Orgao;
import br.unoeste.ativooperante.db.repository.DenunciaRepository;
import br.unoeste.ativooperante.db.repository.OrgaoRepository;
import br.unoeste.ativooperante.services.OrgaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/admin/")
public class AdminRestController {

    @Autowired
    OrgaoService orgaoService;

    @GetMapping(value = "teste-admin")
    public String testeadm() {
        return "conectado";
    }
    // demais api


    @DeleteMapping("/delete-orgao")
    public ResponseEntity<Object> excluirOrgao(@RequestParam(value = "id") Long id) {
        if(orgaoService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-orgao")
    public ResponseEntity<Object> addOrgao (@RequestBody Orgao orgao)
    {
        Orgao novo;
        novo = orgaoService.save(orgao);
        return new ResponseEntity<>(orgao,HttpStatus.OK);
    }

    @GetMapping("/get-orgao")
    public ResponseEntity<Object> buscarUm(@RequestParam(value = "id") Long id) {
        Orgao orgao;
        orgao = orgaoService.getById(id);
        if(orgao == null)
             orgao = new Orgao();
        return new ResponseEntity<>(orgao,HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-orgaos")
    public ResponseEntity<Object> buscarTodos()
    {
        return new ResponseEntity<>(orgaoService.getAll(),HttpStatus.OK);
    }

}
