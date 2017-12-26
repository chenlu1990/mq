package com.example.demo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Created by chenlu on 2017/12/26.
 */
//发布-订阅模式：消息生产者
public class JMSProducerII {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String BORKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final int SENDNUM = 10;//发送的消息数量

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer messageProducer;
        connectionFactory = new ActiveMQConnectionFactory(JMSProducerII.USERNAME,JMSProducerII.PASSWORD,JMSProducerII.BORKEURL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("FirstTopic");//创建消息发布者
            messageProducer = session.createProducer(destination);
            sendMessage(session,messageProducer);
            session.commit();
        } catch (JMSException e) {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }

    }
    public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException {
        for(int i = 0; i < JMSProducerII.SENDNUM; i++){
            TextMessage textMessage = session.createTextMessage("ActiveMQ 发布消息："+i);
            System.out.println("发送消息：ActiveMQ发布消息"+i);
            messageProducer.send(textMessage);
        }

    }
}
