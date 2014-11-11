package net.eduwill.prospring.ch16.jms;

import net.eduwill.prospring.ch16.jms.sender.MessageSender;

import org.springframework.context.support.GenericXmlApplicationContext;

public class JmsSenderSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jms-sender-app-context.xml");
		ctx.refresh();
		
		MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);
		
		messageSender.sendMessage("Yeonho Jang Test JMS message");

	}

}
