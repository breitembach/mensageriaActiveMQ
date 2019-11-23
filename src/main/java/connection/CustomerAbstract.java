package connection;

import constants.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;

/**
 * @apiNote Aqui a classe responsável por fazer a conexão com activeMQ e listener as mensagems COMSUMIDOR
 * **/
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

        // Seta um MessageListener para sobrescrever o método e receber um listener de mensagens
        subscriber.setMessageListener(this);

        //Starta conexao
        connection.start();
    }

    // esse tipo de classe não vamos permitir sobrescrever
    public final void disconnect() throws JMSException {
        this.connection.close();
    }
}
