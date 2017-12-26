package com.example.demo.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by chenlu on 2017/12/26.
 */
//消息监听-订阅者一
public class ListenerII1 implements MessageListener{
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者一收到消息：" + ((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
