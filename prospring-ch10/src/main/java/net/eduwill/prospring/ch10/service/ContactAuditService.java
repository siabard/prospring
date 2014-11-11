package net.eduwill.prospring.ch10.service;

import java.util.List;

import net.eduwill.prospring.ch10.domain.ContactAudit;

public interface ContactAuditService {

	public List<ContactAudit> findAll();
	
	public ContactAudit findById(Long id);
	
	public ContactAudit save(ContactAudit contact);
	
	public ContactAudit findAuditbyrevision(Long id, int revision);
	
}
