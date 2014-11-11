package net.eduwill.prospring.ch16.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="CONTACt")
public class Contact implements Serializable {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private DateTime birthDate;
	
	private int version;

	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="BIRTH_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}

	@Version
	@Column(name="VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String toString() {
		
		return "Contact - Id : " + id
				+ ", First name: " + firstName
				+ ", Last name: " + lastName
				+ ", Birthday: " + birthDate;
		
	}
}
