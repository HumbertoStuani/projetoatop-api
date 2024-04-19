package br.unoeste.ativooperante.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Long id;
    @Column(name = "free_texto")
    private String texto;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "den_id")
    private Denuncia denuncia;
    public Feedback(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Feedback()
    {
        this(0L,"");
    }
}
