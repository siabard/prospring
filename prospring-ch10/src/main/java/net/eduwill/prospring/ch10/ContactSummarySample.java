package net.eduwill.prospring.ch10;

import java.util.List;

import net.eduwill.prospring.ch10.domain.ContactSummary;
import net.eduwill.prospring.ch10.service.ContactSummaryService;
import net.eduwill.prospring.ch10.service.jpa.ContactSummaryUntypeImpl;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ContactSummarySample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		ContactSummaryUntypeImpl contactSummaryUntype =
				ctx.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
		contactSummaryUntype.displayAllContactSummary();

		System.out.println("Contact summary with constructor expression");
		// Contact summary with constructor expression
		ContactSummaryService contactSummaryService =
				ctx.getBean("contactSummaryService", ContactSummaryService.class);
		List<ContactSummary> contacts = contactSummaryService.findAll();
		
		for (ContactSummary contact: contacts) {
			System.out.println(contact);
		}
	}

}
