package net.eduwill.prospring.ch10.repository;

import java.util.List;

import net.eduwill.prospring.ch10.domain.Contact;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	public List<Contact> findByFirstName(String firstName);
	
	public List<Contact> findByFirstNameAndLastName( String firstName, String lastName);

}
