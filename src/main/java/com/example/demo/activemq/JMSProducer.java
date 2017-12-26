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
//点对点模式：生产者
public class JMSProducer {
    //默认链接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认链接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认链接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //默认链接数量
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
        //链接工厂
        ConnectionFactory connectionFactory;
        //链接
        Connection connection = null;
        //会话
        Session session;
        //消息的目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;
        //实例化消息生成者
        connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME,JMSProducer.PASSWORD,JMSProducer.BROKEURL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            //创建Session
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("HelloWorld");
            messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session,messageProducer);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
        for(int i = 0; i < JMSProducer.SENDNUM; i++){
            //创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            //通过消息生成者发出消息
            messageProducer.send(message);
        }
    }
}
