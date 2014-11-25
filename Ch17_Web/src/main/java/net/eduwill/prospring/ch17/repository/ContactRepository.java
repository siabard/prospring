package net.eduwill.prospring.ch17.repository;

import net.eduwill.prospring.ch17.domain.Contact;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
