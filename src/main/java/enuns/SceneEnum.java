package enuns;


public enum SceneEnum {
    MAIN("main", "tela inicial"),
    CHAT("chat", "tela do chat");

    String name;
    String description;


    SceneEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
