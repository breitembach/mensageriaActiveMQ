package views;

import controller.ChatConsumer;
import controller.ChatPublisher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import listViews.MessageCell;
import models.CustomMessage;
import utils.Utils;

import javax.jms.JMSException;
/**
 * @apiNote interface com o chat
 * **/
public class ChatController {

    public Button btnSendMessage;
    public ListView<CustomMessage> listView;
    public TextField textFieldMessage;
    private ChatPublisher publisher;
    private ChatConsumer consumer;
    public TextField privateTextField;

    @FXML
    public void initialize() {

        listView.setCellFactory(messageListView -> new MessageCell());
        try {
            //obtem a instancia já feita anteriormente é usado singleton para não duplicar a conexão
            consumer = ChatConsumer.getInstance();
            publisher = ChatPublisher.getInstance();
            // adiciona mensagem ao chat com o listener
            consumer.setListView(listView);

            // boas vindas ao entrar ao chat
            publisher.sendWelcome(listView);
        } catch (JMSException e) {
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("OPS!!");
            dialogoErro.setHeaderText("ActiveMQ ERROR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }

    }

    @FXML
    void onSendMessage() {
        sendMessage();
    }

    @FXML
    private void sendMessageOnPress(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    private void sendMessage() {
        if(Utils.isNullEmpty(privateTextField.getText())) {
            publisher.sendGlobal(textFieldMessage.getText());
        } else {
            publisher.sendPrivate(textFieldMessage.getText(), privateTextField.getText());
        }
        textFieldMessage.setText(null);
    }


}
