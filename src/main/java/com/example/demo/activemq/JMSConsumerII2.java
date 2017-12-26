package com.example.demo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Created by chenlu on 2017/12/26.
 */
//发布-订阅模式:消息订阅者2
public class JMSConsumerII2 {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;
        connectionFactory = new ActiveMQConnectionFactory(JMSConsumerII2.USERNAME, JMSConsumerII2.PASSWORD, JMSConsumerII2.BROKEURL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();//启动链接
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("FirstTopic");
            messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new ListenerII2());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
