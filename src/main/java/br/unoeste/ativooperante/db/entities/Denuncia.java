package br.unoeste.ativooperante.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "denuncia")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "den_id")
    private Long id;
    @Column(name = "den_titulo")
    private String titulo;
    @Column(name = "den_texto")
    private String texto;
    @Column(name = "den_urgencia")
    private int urgencia;

    @Column(name = "den_data")
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "tip_id", nullable = false)
    private Tipo tipo;
    @ManyToOne
    @JoinColumn(name = "usu_id", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "org_id", nullable = false)
    private Orgao orgao;

    @OneToOne(mappedBy = "denuncia")
    private Feedback feedback;

    public Denuncia(Long id, String titulo, String texto, int urgencia, LocalDate data, Tipo tipo, Usuario usuario, Orgao orgao) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.urgencia = urgencia;
        this.data = data;
        this.tipo = tipo;
        this.usuario = usuario;
        this.orgao = orgao;
    }

    public Denuncia ()
    {
        this(0L,"","",0,null,null,null,null);
    }
}
