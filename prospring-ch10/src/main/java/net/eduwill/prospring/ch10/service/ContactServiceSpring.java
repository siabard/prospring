package net.eduwill.prospring.ch10.service;

import java.util.List;

import net.eduwill.prospring.ch10.domain.Contact;

public interface ContactServiceSpring {

	public List<Contact> findAll();
	
	public List<Contact> findByFirstName(String firstName);
	
	public List<Contact> findByFirstNameAndLastName( String firstName, String lastName);
}
