package net.eduwill.prospring.ch10.springjpa.auditor;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareBean implements AuditorAware<String> {

	public String getCurrentAuditor() {
		return "Yeonho Jang";
	}
	

}
