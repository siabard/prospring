package net.eduwill.prospring.ch10.service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.eduwill.prospring.ch10.domain.ContactSummary;
import net.eduwill.prospring.ch10.service.ContactSummaryService;

@Service("contactSummaryService")
@Repository
@Transactional
public class ContactSummaryServiceImpl implements ContactSummaryService {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<ContactSummary> findAll() {
		List<ContactSummary> result =
				em.createQuery(" SELECT NEW net.eduwill.prospring.ch10.domain.ContactSummary( "
						+ " c.firstName, c.lastName, t.telNumber) "
						+ " from Contact c LEFT JOIN c.contactTelDetails t "
						+ " WHERE t.telType = 'Home' "
						, ContactSummary.class).getResultList();
		return result;
				
	}

}
