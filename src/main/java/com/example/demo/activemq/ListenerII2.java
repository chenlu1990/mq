package com.example.demo.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by chenlu on 2017/12/26.
 */
//消息监听-订阅者二
public class ListenerII2 implements MessageListener{
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者二收到消息：" + ((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
