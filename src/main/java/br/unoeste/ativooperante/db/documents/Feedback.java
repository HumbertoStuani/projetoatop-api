package br.unoeste.ativooperante.db.documents;


import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Document(collection = "feedback")
public class Feedback {

    @Id
    private String id;
    @Field("texto")
    private String texto;

    @DBRef(lazy = true)
    @JsonBackReference
    private Denuncia denuncia;

    @JsonIgnore
    private Object target;
    @JsonIgnore
    private Object source;

    @JsonProperty("denunciaId")
    public String getDenunciaId() {
        return denuncia != null ? denuncia.getId() : null;
    }

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
