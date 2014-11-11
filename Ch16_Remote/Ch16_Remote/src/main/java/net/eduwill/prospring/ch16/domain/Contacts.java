package net.eduwill.prospring.ch16.domain;

import java.io.Serializable;
import java.util.List;

public class Contacts implements Serializable {

	private List<Contact> contacts;
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contacts() {
		
	}
	
	public Contacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
