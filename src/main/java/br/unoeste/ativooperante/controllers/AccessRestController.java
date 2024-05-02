package br.unoeste.ativooperante.controllers;

import br.unoeste.ativooperante.db.entities.Usuario;
import br.unoeste.ativooperante.secutiry.JWTTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/security/")
public class AccessRestController
{
    @PostMapping(value = "/logar")
    public ResponseEntity<Object> logar(@RequestBody Usuario usuario)
    {
        String token = "não autenticado";
        // acesso ao BD para verificar a existencia do usuario
        //comparar a senha

        if(usuario.getSenha().equals("123"))
        {
            token = JWTTokenProvider.getToken(usuario.getEmail(), ""+usuario.getNivel());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body(token);
    }

}
