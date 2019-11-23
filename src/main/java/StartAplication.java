import constants.Constants;
import controller.ChatConsumer;
import controller.ChatPublisher;
import controller.SceneController;
import controller.UserController;
import enuns.SceneEnum;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.ChatUser;

import javax.jms.JMSException;
import java.util.concurrent.ExecutionException;

/**
 * @apiNote Inicialização do app e comunicação da primeira tela
 * **/
public class StartAplication extends Application {
    public Stage window;
    public TextField textFieldFullName;
    public Button buttonInit;
    private SceneController sceneCon = SceneController.getInstance();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Entrar no Chat");
        stage.setScene(sceneCon.getScene(SceneEnum.MAIN));
        stage.show();
    }

    @FXML
    public void onInitChat(ActionEvent event) {
        try {
            UserController.setInstance(new ChatUser(textFieldFullName.getText()));
            ChatConsumer.getInstance(); // conecta ao ACTIVEMQ
            ChatPublisher.getInstance(); // conecta ao ACTIVEMQ
        } catch (JMSException e) {
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("OPS!!");
            dialogoErro.setHeaderText("ActiveMQ ERROR");
            dialogoErro.setContentText(e.getMessage() + " Conecte o ActiveMQ na porta: " + Constants.URL_CONNECTION);
            dialogoErro.showAndWait();
            return;
        }
        try {
            // mudança de scena
            window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Chat");
            window.setScene(sceneCon.getScene(SceneEnum.CHAT));
            window.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        try {
            ChatPublisher.getInstance().disconnect();
            ChatConsumer.getInstance().disconnect();
        }catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        launch();
    }

}
