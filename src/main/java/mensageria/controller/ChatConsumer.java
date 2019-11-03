package mensageria.controller;


import javafx.scene.control.ListView;
import mensageria.models.ChatUser;
import mensageria.models.CustomMessage;
import mensageria.utils.FormatChatMessage;

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
    private ChatUser user;

    public ChatConsumer(ChatUser user, ListView<CustomMessage> listView) throws JMSException {
        super();
        this.listView = listView;
        this.user = user;
    }

    public static synchronized ChatConsumer getInstance(ChatUser user, ListView<CustomMessage> listView) throws JMSException {
        if(instance == null) {
            instance = new ChatConsumer(user, listView);
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
//            else if (type.equals(FormatChatMessage.TYPE_PRIVATE) &&
//                    (message.getStringProperty("Destinatario").equals(me.getUUID()) || message.getStringProperty(FormatChatMessage.REMETENTUID).equals(me.getUUID())))
//                listView.getItems().add(FormatChatMessage.formatPrivateMessage(message));

        } catch (Exception e) {
            System.out.println("Erro onMessage: " + e.getMessage());
        }
    }

}
