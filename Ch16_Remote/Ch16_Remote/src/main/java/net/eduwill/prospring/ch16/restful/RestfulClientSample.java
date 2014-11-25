package net.eduwill.prospring.ch16.restful;

import net.eduwill.prospring.ch16.domain.Contact;
import net.eduwill.prospring.ch16.domain.Contacts;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class RestfulClientSample {

	private static final String URL_GET_ALL_CONTACTS = "http://localhost:8080/prospring/restful/contact/listdata";
	private static final String URL_GET_CONTACT_BY_ID = "http://localhost:8080/prospring/restful/contact/{id}";
	private static final String URL_CREATE_CONTACT = "http://localhost:8080/prospring/restful/contact";
	private static final String URL_UPDATE_CONTACT = "http://localhost:8080/prospring/restful/contact/{id}";
	private static final String URL_DELETE_CONTACT = "http://localhost:8080/prospring/restful/contact/{id}";
			
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:restful-client-app-context.xml");
		ctx.refresh();
		
		Contacts contacts;
		Contact contact;
		
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
		
		System.out.println("Testing retrieve all contacts:");
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);
		
		
		// Test Retrieve contact by id
		System.out.println("Testing retriev a contact by id :");
		
		contact = restTemplate.getForObject(URL_GET_CONTACT_BY_ID, Contact.class, 1);
		System.out.println(contact);
		
		System.out.println("");

	}

	private static void listContacts(Contacts contacts) {
		for(Contact contact: contacts.getContacts()) {
			System.out.println(contact);
		}
		
		System.out.println();
		
	}
	
	

}
