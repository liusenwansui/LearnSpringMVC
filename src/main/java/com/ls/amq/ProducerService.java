package com.ls.amq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * 生产者服务 两个发送方式，一个是发送到默认的目的地，一个是根据目的地发送消息。
 */
@Service
public class ProducerService {

  @Resource(name = "jmsTemplate")
  private JmsTemplate jmsTemplate;

  public void sendMessage(Destination destination, final String msg) {
    System.out.println(Thread.currentThread().getName() + " 向队列" + destination.toString()
        + "发送消息---------------------->" + msg);
    jmsTemplate.send(destination, new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage(msg);
      }
    });
  }

  public void sendMessage(final String msg) {
    Destination destination = jmsTemplate.getDefaultDestination();
    System.out.println(Thread.currentThread().getName() + " 向队列" + destination.toString()
        + "发送消息---------------------->" + msg);
    jmsTemplate.send(new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage(msg);
      }
    });
  }
}
