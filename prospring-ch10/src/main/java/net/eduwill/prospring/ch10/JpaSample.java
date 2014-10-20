package net.eduwill.prospring.ch10;

import java.util.Date;
import java.util.List;
import java.util.Set;

import net.eduwill.prospring.ch10.domain.Contact;
import net.eduwill.prospring.ch10.domain.ContactTelDetail;
import net.eduwill.prospring.ch10.domain.Hobby;
import net.eduwill.prospring.ch10.service.ContactService;

import org.springframework.context.support.GenericXmlApplicationContext;

public class JpaSample {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();

		ContactService contactService = ctx.getBean("jpaContactService", ContactService.class);

		// List Contacts without details
		List<Contact> contacts = contactService.findAllWithDetail();

		listContactsWithDetail(contacts);


		// Add new contact

		Contact contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "11111111");
		contact.addContactTelDetail(contactTelDetail);

		contactTelDetail = new ContactTelDetail("Mobile", "22222222");
		contact.addContactTelDetail(contactTelDetail);
		contactService.save(contact);

		contacts = contactService.findAllWithDetail();

		listContactsWithDetail(contacts);

		// Update contact
		contact = contactService.findById(1L);
		System.out.println();
		System.out.println("Contact with id 1: " + contact);
		System.out.println();

		contact.setFirstName("Kim");
		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		ContactTelDetail toDeleteContactTelDetail = null;

		for (ContactTelDetail contactTel: contact.getContactTelDetails()) {
			if (contactTel.getTelType().equals("Home")) {
				toDeleteContactTelDetail = contactTel;
			}
		}

		contactTels.remove(toDeleteContactTelDetail);
		contactService.save(contact);

		contacts = contactService.findAllWithDetail();

		listContactsWithDetail(contacts);

		// Delete contact
		
		contact = contactService.findById(1L);
		contactService.delete(contact);
		
		contacts = contactService.findAllWithDetail();

		listContactsWithDetail(contacts);
		
		
		// NAtive Test
		contacts = contactService.findAllByNativeQuery();

		listContacts(contacts);
		
		
		// Criteria Test
		System.out.println("========================================");
		System.out.println("Criteria Test");
		contacts = contactService.findByCriteriaQuery("Mijung", "Hong");
		listContactsWithDetail(contacts);
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