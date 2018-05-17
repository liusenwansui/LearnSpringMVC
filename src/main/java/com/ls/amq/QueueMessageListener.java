package com.ls.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听器
 */
public class QueueMessageListener implements MessageListener {

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println("QueueMessageListener监听到了文本消息：\t" + tm.getText());
				// do something ...
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("消息未处理：" + message.toString());
		}
	}
}
