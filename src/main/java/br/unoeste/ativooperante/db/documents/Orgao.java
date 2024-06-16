package br.unoeste.ativooperante.db.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "orgao")
public class Orgao {
    @Id
    private String id;
    @Field("nome")
    private String nome;

    public Orgao(String nome) {
        this.nome = nome;
    }

    public Orgao()
    {
        this("");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
