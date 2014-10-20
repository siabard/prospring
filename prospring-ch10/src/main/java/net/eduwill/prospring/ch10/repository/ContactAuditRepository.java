package net.eduwill.prospring.ch10.repository;

import net.eduwill.prospring.ch10.domain.ContactAudit;

import org.springframework.data.repository.CrudRepository;

public interface ContactAuditRepository extends CrudRepository<ContactAudit, Long> {
	

}
