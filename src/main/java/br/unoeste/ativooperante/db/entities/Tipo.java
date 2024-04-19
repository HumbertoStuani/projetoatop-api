package br.unoeste.ativooperante.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private Long id;

    @Column(name = "tip_nome")
    private String nome;

    public Tipo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Tipo()
    {
        this.id = 0L;
        this.nome = "";
    }
}
