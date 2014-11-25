package net.eduwill.prospring.ch17.service;

import java.util.List;

import net.eduwill.prospring.ch17.domain.Contact;

public interface ContactService {
	
	public List<Contact> findAll();
	
	public Contact findById(Long id);
	
	public Contact save(Contact contact);

}
