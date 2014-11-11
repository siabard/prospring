package net.eduwill.prospring.ch16.service;

import java.util.List;

import net.eduwill.prospring.ch16.domain.Contact;

public interface ContactService {

	public List<Contact> findAll();
	
	public Contact findById(Long id);
	
	public List<Contact> findByFirstName(String firstName);
	
	public Contact save(Contact contact);
	
	public void delete(Contact contact);
}
