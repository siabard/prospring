package net.eduwill.prospring.ch10;

import java.util.List;

import net.eduwill.prospring.ch10.domain.Contact;
import net.eduwill.prospring.ch10.domain.ContactTelDetail;
import net.eduwill.prospring.ch10.domain.Hobby;
import net.eduwill.prospring.ch10.service.ContactServiceSpring;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJpaSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring-data-app-context.xml");
		ctx.refresh();
		
		ContactServiceSpring contactService = ctx.getBean("springJpaContactService", ContactServiceSpring.class);
		
		
		System.out.println("Find all contacts.");
		List<Contact> contacts = contactService.findAll();
		listContacts(contacts);
		
		System.out.println("Find contacts by First name");		
		contacts = contactService.findByFirstName("Yeonho");
		listContacts(contacts);
		
		System.out.println("Find contacts by first name and last name");
		contacts = contactService.findByFirstNameAndLastName("Yeonho",  "Jang");
		listContacts(contacts);

	}

	private static void listContacts(List<Contact> contacts) {
		System.out.println();
		System.out.println("Listing contacts without details:");
		for (Contact contact: contacts) {
			System.out.println( contact );
			System.out.println();
		}
	}

	private static void listContactsWithDetail(List<Contact> contacts) {
		System.out.println();
		System.out.println("Listing contacts with details:");
		for (Contact contact: contacts) {
			System.out.println( contact );

			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}

			if (contact.getHobbies() != null) {
				for (Hobby hobby : contact.getHobbies()) {
					System.out.println(hobby);
				}
			}

			System.out.println();
		}
	}
	
}
