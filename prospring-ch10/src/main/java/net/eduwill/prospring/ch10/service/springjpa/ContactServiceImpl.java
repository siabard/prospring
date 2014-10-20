package net.eduwill.prospring.ch10.service.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.eduwill.prospring.ch10.domain.Contact;
import net.eduwill.prospring.ch10.repository.ContactRepository;
import net.eduwill.prospring.ch10.service.ContactServiceSpring;

@Service("springJpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactServiceSpring {

	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}

	public List<Contact> findByFirstName(String firstName) {
		return contactRepository.findByFirstName(firstName);
	}

	public List<Contact> findByFirstNameAndLastName(String firstName,
			String lastName) {
		return contactRepository.findByFirstNameAndLastName(firstName,  lastName);
	}

}
