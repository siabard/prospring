package net.eduwill.prospring.ch17.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.eduwill.prospring.ch17.domain.Contact;
import net.eduwill.prospring.ch17.repository.ContactRepository;
import net.eduwill.prospring.ch17.service.ContactService;

public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}

	@Override
	public Contact findById(Long id) {
		
		return contactRepository.findOne(id);
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

}
