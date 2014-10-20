package net.eduwill.prospring.ch10.service;

import java.util.List;

import net.eduwill.prospring.ch10.domain.ContactSummary;

public interface ContactSummaryService {

	public List<ContactSummary> findAll();
}
