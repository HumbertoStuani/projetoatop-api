package br.unoeste.ativooperante.db.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "usuario")

public class Usuario {

    @Id
    private String id;
    @Indexed(unique = true)
    @Field("cpf")
    private String cpf;
    @Indexed(unique = true)
    @Field("email")
    private String email;
    @Field("senha")
    private String senha;
    @Field("nivel")
    private int nivel;


    public Usuario(String cpf, String email, String senha, int nivel) {
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario()
    {
        this("","","",0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
