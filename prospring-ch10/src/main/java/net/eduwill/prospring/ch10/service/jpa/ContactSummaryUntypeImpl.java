package net.eduwill.prospring.ch10.service.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("contactSummaryUntype")
@Repository
@Transactional
public class ContactSummaryUntypeImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public void displayAllContactSummary() {
		List result = em.createQuery("SELECT c.firstName, c.lastName, t.telNumber "
				+ " FROM Contact c LEFT JOIN c.contactTelDetails t "
				+ " WHERE t.telType = 'Home' "
				).getResultList();
		
		int count = 0;
		for (Iterator i = result.iterator(); i.hasNext();) {
			Object[] values = (Object[]) i.next();
			System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
		}
	}
}
