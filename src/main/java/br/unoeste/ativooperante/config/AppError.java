package br.unoeste.ativooperante.config;

public class AppError {
    private String message;

    public AppError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
