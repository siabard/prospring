package net.eduwill.prospring.ch17.repository;

import net.eduwill.prospring.ch17.domain.Contact;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

}
