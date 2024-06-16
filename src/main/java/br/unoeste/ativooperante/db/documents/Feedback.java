package br.unoeste.ativooperante.db.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "feedback")
public class Feedback {

    @Id
    private String id;
    @Field("texto")
    private String texto;

    @DBRef(lazy = true)
    private Denuncia denuncia;

    public Feedback(String texto) {
        this.texto = texto;
    }

    public Feedback()
    {
        this("");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }
}
