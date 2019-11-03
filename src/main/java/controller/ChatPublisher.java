package controller;

import connection.PublisherAbstract;
import javafx.scene.control.ListView;
import models.ChatUser;
import models.CustomMessage;
import utils.FormatChatMessage;

import javax.jms.JMSException;
import javax.jms.TextMessage;


/**
 * Classe Produtora responsável pela publicação no Topic.
 * @implNote conexão com activeMQ
 */
public class ChatPublisher extends PublisherAbstract {
    private static ChatPublisher instance;
    private UserController userController = UserController.getInstance();

    /**
     * @apiNote aqui não podemos deixar usar o construtor para não ter multiplas conexões, deve usar o getInstance
     */
    private ChatPublisher() throws JMSException {
    }

    /**
     * @apiNote deve sempre usar o getInstance para não ter multiplas conexões
     */
    public static synchronized ChatPublisher getInstance() throws JMSException {
        if(instance == null) {
            instance = new ChatPublisher();
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
            TextMessage message = FormatChatMessage.makeMessage(session, userController.getUser(), text, destinatarioUUID);
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
            TextMessage message = FormatChatMessage.makeMessage(session, userController.getUser(), text);
            publisher.publish(message);

        } catch (JMSException e) {
            System.err.println("Exception send - " + e.getMessage());
        }
    }

    public void sendWelcome(ListView<CustomMessage> listView) {
        try {
            TextMessage message = FormatChatMessage.makeWelcomeMessage(session, userController.getUser());
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

    public void sendMessageDisconnect() throws JMSException {
        ChatUser user = userController.getUser();
        TextMessage message = FormatChatMessage.makeMessage(session, user, user.getName()+" acabou de disconectar!");
        publisher.publish(message);

    }

    @Override
    public void disconnect() throws JMSException {
        sendMessageDisconnect();
        super.disconnect();

    }
}
