package controller;

import enuns.SceneEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
/**
 * @apiNote Scene util para obter as telas
 * **/
public class SceneController {
    private static FXMLLoader loader = new FXMLLoader();
    private SceneController() { }

    public static SceneController instance;

    public static SceneController getInstance() {
        if(instance == null) {
            instance = new SceneController();
        }
        return instance;
    }

    public Scene getScene(SceneEnum scene) {

        try {
            switch (scene) {
                case MAIN:
                    return new Scene(loader.load(getClass().getResource("/layout/main.fxml")));
                case CHAT:
                    return new Scene(loader.load(getClass().getResource("/layout/chat-layout.fxml")));
                default:
                    throw new IOException("Não foi possível encontrar a cena!");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
