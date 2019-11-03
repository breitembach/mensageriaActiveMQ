package mensageria.utils;


import mensageria.models.ChatUser;
import mensageria.models.CustomMessage;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Classe auxiliar para manipulação e formatação de mensagens
 *
 * @author Master
 */
public class FormatChatMessage {

    public static final String TYPE_WELCOME = "Welcome";
    public static final String TYPE_GLOBAL = "Global";
    public static final String TYPE_PRIVATE = "Private";

    public static final String REMETENTNAME = "remetenteName";
    public static final String REMETENTUID = "remetenteUID";
    public static final String MESSAGE = "message";

    public static final String DESTINATION = "destinatario";



    /**
     * Cria uma mensagem de boas vindas encapsulando as devidas propriedades
     *
     * @param session Session criada a partir da conexão com o broker
     * @param me O pseudo usuário remetente
     *
     * @return TextMessage mensagem a ser publicada pelo publisher
     */
    public static TextMessage makeWelcomeMessage(Session session, ChatUser me) throws JMSException {

        TextMessage message = null;
        message = session.createTextMessage();
        message.setJMSType(TYPE_WELCOME);
        message.setStringProperty(REMETENTNAME, me.getName());
        message.setStringProperty(REMETENTUID, me.getUUID());
        message.setStringProperty(MESSAGE, "Bem vindo a sala!");

        return message;
    }

    /**
     * Cria uma mensagem global encapsulando as devidas propriedades
     *
     * @param session Session criada a partir da conexão com o broker
     * @param me O pseudo usuário remetente
     * @param text Texto do corpo da mensagem
     *
     * @return TextMessage mensagem a ser publicada pelo publisher
     */
    public static TextMessage makeMessage(Session session, ChatUser me, String text) throws JMSException {

        TextMessage message = null;

        message = session.createTextMessage();
        message.setText(text);
        message.setJMSType(TYPE_GLOBAL);
        message.setStringProperty(REMETENTNAME, me.getName());
        message.setStringProperty(REMETENTUID, me.getUUID());

        return message;
    }



    /**
     * Cria uma mensagem privada encapsulando as devidas propriedades
     *
     * @param session Session criada a partir da conexão com o broker
     * @param me O pseudo usuário remetente
     * @param text Texto do corpo da mensagem
     * @param destinatario O pseudo usuário destinatário
     *
     * @return TextMessage mensagem a ser publicada pelo publisher
     */
    public static TextMessage makeMessage(Session session, ChatUser me, String text, String destinatario) {

        TextMessage message = null;

        try {
            message = session.createTextMessage();
            message.setText(text);
            message.setJMSType(TYPE_PRIVATE);
            message.setStringProperty(REMETENTNAME, me.getName());
            message.setStringProperty(REMETENTUID, me.getUUID());
            message.setStringProperty(DESTINATION, destinatario);
        } catch (JMSException e) {
            System.err.println("Erro ao criar mensagem: " + e.getMessage());
        }

        return message;
    }

    /**
     * Formata uma mensagem global recebida para ser printada
     *
     * @param message mensagem
     * @return String formatada pronta para ser exibida
     */
    public static CustomMessage formatMessage(TextMessage message) throws JMSException {
        return new CustomMessage(
                message.getText(),
                message.getStringProperty(REMETENTUID),
                message.getStringProperty(REMETENTNAME)
        );
    }

    /**
     * Formata uma mensagem privada recebida para ser printada
     *
     * @param message mensagem
     * @return String formatada pronta para ser exibida
     */
    public static CustomMessage formatPrivateMessage(TextMessage message) throws JMSException {
        return new CustomMessage(
                "Recebendo mensagem privada: " + message.getText(),
                message.getStringProperty(REMETENTUID),
                message.getStringProperty(REMETENTNAME)
        );
    }

    /**
     * Formata uma mensagem de boas vindas
     *
     * @param message mensagem
     * @return String formatada pronta para ser exibida
     */
    public static CustomMessage formatWelcomeMessage(TextMessage message) throws JMSException {

        return new CustomMessage(
                "acabou de entrar na sala!",
                message.getStringProperty(REMETENTUID),
                message.getStringProperty(REMETENTNAME)
        );
    }
}
