package br.unoeste.ativooperante.db.documents;


import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "denuncia")
public class Denuncia {

    @Id
    private String id;
    @Field("titulo")
    private String titulo;
    @Field("texto")
    private String texto;
    @Field("urgencia")
    private int urgencia;
    @Field("data")
    private LocalDate data;
    @Field("imagem")
    private byte[] imagem;
    @DBRef
    private Tipo tipo;
    @DBRef
    @JsonIgnoreProperties("senha")
    private Usuario usuario;
    @DBRef
    private Orgao orgao;
    @DBRef
    @JsonManagedReference
    private Feedback feedback;


    public Denuncia(String titulo, String texto, int urgencia, LocalDate data, Tipo tipo, Usuario usuario, Orgao orgao, byte[] imagem) {
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
        this("","",0,LocalDate.now(),null,null,null, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
