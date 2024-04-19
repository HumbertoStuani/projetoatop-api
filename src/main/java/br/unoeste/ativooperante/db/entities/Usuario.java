package br.unoeste.ativooperante.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;
    @Column(name = "usu_cpf")
    private String cpf;
    @Column(name = "usu_email")
    private String email;
    @Column(name = "usu_senha")
    private String senha;
    @Column(name = "usu_nivel")
    private int nivel;


    public Usuario(Long id, String cpf, String email, String senha, int nivel) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario()
    {
        this(0L,"","","",0);
    }
}
