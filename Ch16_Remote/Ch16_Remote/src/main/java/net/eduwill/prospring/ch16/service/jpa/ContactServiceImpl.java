package net.eduwill.prospring.ch16.service.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.eduwill.prospring.ch16.domain.Contact;
import net.eduwill.prospring.ch16.repository.ContactRepository;
import net.eduwill.prospring.ch16.service.ContactService;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

	private Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		return Lists.newArrayList( contactRepository.findAll() );
	}

	@Override
	@Transactional(readOnly=true)
	public Contact findById(Long id) {
		
		return contactRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Contact> findByFirstName(String firstName) {
		return contactRepository.findByFirstName(firstName);
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public void delete(Contact contact) {
		contactRepository.delete(contact);
		
	}

}
