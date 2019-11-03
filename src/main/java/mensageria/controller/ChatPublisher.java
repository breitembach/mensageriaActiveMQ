package mensageria.controller;

import javafx.scene.control.ListView;
import mensageria.models.ChatUser;
import mensageria.models.CustomMessage;
import mensageria.utils.FormatChatMessage;

import javax.jms.JMSException;
import javax.jms.TextMessage;


/**
 * Classe Produtora responsável pela publicação no Topic.
 *
 * @author Master
 */
public class ChatPublisher extends PublisherAbstract {
    private static ChatPublisher instance;
    private final ChatUser user;
    private ListView<CustomMessage> listView;

    /**
     *
     * @param user Pseudo usuário para identificação de mensagem.
     */
    public ChatPublisher(ChatUser user, ListView<CustomMessage> listView) throws JMSException {
        super();
        this.user = user;
        this.listView = listView;
    }

    public static synchronized ChatPublisher getInstance(ChatUser user, ListView<CustomMessage> listView) throws JMSException {
        if(instance == null) {
            instance = new ChatPublisher(user, listView);
        }
        return instance;
    }

    /**
     * Envia mensagem privada
     *
     * @param text O texto da mensagem
     * @param destinatarioUUID O uuid do pseudo usuário que irá receber a mensagem.
     */
    public void sendPrivate(String text, String destinatarioUUID) {
        try {
            TextMessage message = FormatChatMessage.makeMessage(session, user, text, destinatarioUUID);
            publisher.publish(message);
        } catch (JMSException e) {
            System.err.println("Exception send - " + e.getMessage());
        }
    }

    /**
     * Envia mensagem global
     *
     * @param text O texto da mensagem
     */
    public void sendGlobal(String text) {
        try {
            TextMessage message = FormatChatMessage.makeMessage(session, user, text);
            publisher.publish(message);

        } catch (JMSException e) {
            System.err.println("Exception send - " + e.getMessage());
        }
    }

    public void sendWelcome() {
        try {
            TextMessage message = FormatChatMessage.makeWelcomeMessage(session, user);
            publisher.publish(message);
            listView.getItems().add(
                    new CustomMessage(
                        message.getStringProperty(FormatChatMessage.MESSAGE),
                        message.getStringProperty(FormatChatMessage.REMETENTUID),
                        message.getStringProperty(FormatChatMessage.REMETENTNAME)
                    )
            );

        } catch (JMSException e) {
            System.err.println("Exception send - " + e.getMessage());
        }
    }

}
