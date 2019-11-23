package controller;


import connection.CustomerAbstract;
import javafx.scene.control.ListView;
import models.CustomMessage;
import utils.FormatChatMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Classe consumidora assíncrona para um Topic.
 *
 * @author Master
 */
public class ChatConsumer extends CustomerAbstract {
    private static ChatConsumer instance;
    private ListView<CustomMessage> listView;
    private UserController userController = UserController.getInstance();

    public ChatConsumer() throws JMSException {

    }
    // apenas uma conexao por vez
    public static synchronized ChatConsumer getInstance() throws JMSException {
        if(instance == null) {
            instance = new ChatConsumer();
        }
        return instance;
    }

    @Override
    public void onMessage(Message received) {

        try {
            TextMessage message = (TextMessage) received;

            if (message.getJMSType().equals(FormatChatMessage.TYPE_WELCOME))
                listView.getItems().add(FormatChatMessage.formatWelcomeMessage(message));
            else if (message.getJMSType().equals(FormatChatMessage.TYPE_GLOBAL))
                listView.getItems().add(FormatChatMessage.formatMessage(message));
            else if (message.getJMSType().equals(FormatChatMessage.TYPE_PRIVATE) &&
                    message.getStringProperty(FormatChatMessage.DESTINATION).equals(userController.getUser().getUUID()))
                listView.getItems().add(FormatChatMessage.formatPrivateMessage(message));

        } catch (Exception e) {
            System.out.println("Erro onMessage: " + e.getMessage());
        }
    }

    public ListView<CustomMessage> getListView() {
        return listView;
    }

    public void setListView(ListView<CustomMessage> listView) {
        this.listView = listView;
    }

}
