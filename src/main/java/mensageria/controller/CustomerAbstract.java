package mensageria.controller;

import mensageria.constants.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public abstract class CustomerAbstract implements MessageListener {

    private TopicConnectionFactory factory;
    private Topic topic;
    private TopicConnection connection;
    private TopicSession session;
    private TopicSubscriber subscriber;

    public CustomerAbstract() throws JMSException {
        factory = new ActiveMQConnectionFactory(Constants.URL_CONNECTION);

        //Connection
        connection = factory.createTopicConnection();

        //Session
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        //Topic (cria um tópico se não existir)
        topic = session.createTopic(Constants.TOPICNAME);

        //Receptor
        subscriber = session.createSubscriber(topic);
        // Seta um MessageListener para recebimento de mensagens
        subscriber.setMessageListener(this);

        //Starta conexao
        connection.start();
    }
}
