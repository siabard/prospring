package net.eduwill.prospring.ch16.repository;

import java.util.List;

import net.eduwill.prospring.ch16.domain.Contact;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

	public List<Contact> findByFirstName(String firstName);
}
