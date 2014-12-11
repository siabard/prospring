package net.eduwill.prospring.ch17.web.controller;

import java.util.List;

import net.eduwill.prospring.ch17.domain.Contact;
import net.eduwill.prospring.ch17.service.ContactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/contacts")
@Controller
public class ContactController {

	private final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel) {
		
		logger.info("Listing contacts");
		
		List<Contact> contacts = contactService.findAll();
		
		uiModel.addAttribute("contacts",  contacts);
		
		logger.info("No. of contacts:" + contacts.size());
		return "contacts/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		Contact contact = contactService.findById(id);
		uiModel.addAttribute("contact", contact);
		
		return "contacts/view";
	}
}
