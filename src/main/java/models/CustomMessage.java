package models;

public class CustomMessage {
    String message;
    String remetenteUID;
    String remetenteName;

    public CustomMessage(String message, String remetenteUID, String remetenteName) {
        this.message = message;
        this.remetenteUID = remetenteUID;
        this.remetenteName = remetenteName;
    }

    public String getRemetenteUID() {
        return remetenteUID;
    }

    public void setRemetenteUID(String remetenteUID) {
        this.remetenteUID = remetenteUID;
    }

    public String getRemetenteName() {
        return remetenteName;
    }

    public void setRemetenteName(String remetenteName) {
        this.remetenteName = remetenteName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

