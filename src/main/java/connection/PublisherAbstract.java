package connection;

import constants.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;

public abstract class PublisherAbstract {
    private TopicConnectionFactory factory;
    private Topic topic;
    private TopicConnection connection;
    protected TopicSession session;
    protected TopicPublisher publisher;

    public PublisherAbstract() throws JMSException {
        factory = new ActiveMQConnectionFactory(Constants.URL_CONNECTION);

        //Connection
        connection = factory.createTopicConnection();

        //Session
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        //Topic (cria um tópico se não existir)
        topic = session.createTopic(Constants.TOPICNAME);

        //Publisher
        publisher = session.createPublisher(topic);

    }

    public abstract void sendPrivate(String text, String destinatarioUUID);

    public abstract void sendGlobal(String text);

    // poderá disconectar e alem disso, sobrescrever o disconect caso queira notificar o chat
    public void disconnect() throws JMSException {
        this.connection.close();
    }

}
