package net.eduwill.prospring.ch10;

import java.util.Date;
import java.util.List;

import net.eduwill.prospring.ch10.domain.ContactAudit;
import net.eduwill.prospring.ch10.service.ContactAuditService;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJpaAuditSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring-data-app-context.xml");
		ctx.refresh();

		ContactAuditService contactService = ctx.getBean("contactAuditService", ContactAuditService.class);

		List<ContactAudit> contacts = contactService.findAll();
		listContacts(contacts);

		System.out.println("// Add new contact");
		ContactAudit contact = new ContactAudit();
		contact.setFirstName("Yeonho");
		contact.setLastName("Jang");
		contact.setBirthDate(new Date());
		contactService.save(contact);

		contacts = contactService.findAll();
		listContacts(contacts);	

		System.out.println("// Find by id");
		contact = contactService.findById(1L);
		System.out.println("");
		System.out.println("Contact with id 1: " + contact);
		System.out.println("");
		
		// Update contact
		System.out.println("// Update contact");
		contact.setFirstName("Ian");
		contactService.save(contact);
		
		contacts = contactService.findAll();
		listContacts(contacts);	
		
		// Find audit record by revision
		ContactAudit oldContact = contactService.findAuditbyrevision(1L, 1);
		System.out.println("Find Audit record by revision");
		System.out.println("Old Contact with id 1 and rev 1 : " + oldContact);
		System.out.println("");
		

	}



	private static void listContacts(List<ContactAudit> contacts) {
		System.out.println();
		System.out.println("Listing contacts without details:");
		for (ContactAudit contact: contacts) {
			System.out.println( contact );
			System.out.println();
		}
	}

}
