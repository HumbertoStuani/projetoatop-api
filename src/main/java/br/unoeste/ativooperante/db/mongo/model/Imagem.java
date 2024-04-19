package br.unoeste.ativooperante.db.mongo.model;

import br.unoeste.ativooperante.db.entities.Denuncia;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AtivoOperante")
public class Imagem {

    @Id
    private String id;
    private Denuncia denuncia;
    private byte[] dados;

    public Imagem(String id, Denuncia denuncia, byte[] dados) {
        this.id = id;
        this.denuncia = denuncia;
        this.dados = dados;
    }

    public Imagem() {
        this("",null,null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }
}