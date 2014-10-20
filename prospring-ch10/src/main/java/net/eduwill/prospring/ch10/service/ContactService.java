package net.eduwill.prospring.ch10.service;

import java.util.List;

import net.eduwill.prospring.ch10.domain.Contact;

public interface ContactService {
	public List<Contact> findAll();
	
	public List<Contact> findAllWithDetail();
	
	public Contact findById(Long id);
	
	public Contact save(Contact contact);
	
	public void delete(Contact contact);
	
	public List<Contact> findAllByNativeQuery();
	
	public List<Contact> findByCriteriaQuery( String firstName, String lastName);
}
