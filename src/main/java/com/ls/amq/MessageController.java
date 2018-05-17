package com.ls.amq;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 */
@Controller
public class MessageController {
	@Resource(name = "demoQueueDestination")
	private Destination destination;

	// 队列消息生产者
	@Resource(name = "producerService")
	private ProducerService producer;

	// 队列消息消费者
	@Resource(name = "consumerService")
	private ConsumerService consumer;

	// localhost:8080/LearnSpringMVC/SendMessage
	@RequestMapping(value = "/SendMessage", method = RequestMethod.POST)
	@ResponseBody
	public void send(String msg) {
		System.out.println(Thread.currentThread().getName() + "------------send to jms Start");
		producer.sendMessage(msg);
		System.out.println(Thread.currentThread().getName() + "------------send to jms End");
	}

	// localhost:8080/LearnSpringMVC/ReceiveMessage
	@RequestMapping(value = "/ReceiveMessage", method = RequestMethod.GET)
	public void receive(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(Thread.currentThread().getName() + "------------receive from jms Start");
		TextMessage tm = consumer.receive(destination);
		System.out.println(Thread.currentThread().getName() + "------------receive from jms End");
		
		resp.setContentType("application/json; charset=utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = resp.getWriter();
		out.write(tm.getText());
		out.close();
	}

	@RequestMapping(value = "/test")
	public void test(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setContentType("application/json; charset=utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = resp.getWriter();
		out.write("OK");
		out.close();
	}
}