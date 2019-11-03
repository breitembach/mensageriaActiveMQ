package mensageria.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mensageria.listViews.MessageCell;
import mensageria.models.ChatUser;
import mensageria.models.CustomMessage;

import javax.jms.JMSException;

public class Controller {

    public Button btnSendMessage;
    public ListView<CustomMessage> listView;
    public TextField textFieldMessage;
    private ChatPublisher publisher;
    private ChatConsumer consumer;
    private ChatUser user = new ChatUser("Lucas Breitembach");

    @FXML
    public void initialize() throws JMSException {
        listView.setCellFactory(messageListView -> new MessageCell());
        consumer = ChatConsumer.getInstance(user, listView);
        publisher = ChatPublisher.getInstance(user, listView);
        publisher.sendWelcome();
        textFieldMessage.setOnKeyPressed( e -> sendMessageOnPress(e));
    }

    @FXML
    void onSendMessage() throws JMSException {
        publisher = ChatPublisher.getInstance(user, listView);
        publisher.sendGlobal(textFieldMessage.getText());
        textFieldMessage.setText(null);
    }

    private void sendMessageOnPress(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            publisher.sendGlobal(textFieldMessage.getText());
            textFieldMessage.setText(null);
        }
    }

}
