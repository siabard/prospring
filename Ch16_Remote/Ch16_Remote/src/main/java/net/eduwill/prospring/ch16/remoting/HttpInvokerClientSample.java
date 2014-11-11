package net.eduwill.prospring.ch16.remoting;

import java.util.List;

import net.eduwill.prospring.ch16.domain.Contact;
import net.eduwill.prospring.ch16.service.ContactService;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HttpInvokerClientSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:http-invoker-app-context.xml");
		ctx.refresh();
		
		ContactService contactService = ctx.getBean("remoteContactService", ContactService.class);
		
		// Find all
		
		System.out.println("Finding all contacts");
		List<Contact> contacts = contactService.findAll();
		
		listContacts(contacts);
		
		// Find contacts by first name
		System.out.println("Finding contact with first name equals Yeonho");
		
		contacts = contactService.findByFirstName("Yeonho");
		
		listContacts(contacts);

	}

	private static void listContacts(List<Contact> contacts) {
		for (Contact contact: contacts) {
			System.out.println(contact);
		}
		
		System.out.println();
	}

}
