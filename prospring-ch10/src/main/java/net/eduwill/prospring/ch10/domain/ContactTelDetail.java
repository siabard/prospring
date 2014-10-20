package net.eduwill.prospring.ch10.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="contact_tel_detail")
public class ContactTelDetail implements Serializable {
	private Long id;
	private int version;
	private String telType;
	private String telNumber;
	private Contact contact;
	
	public ContactTelDetail() {
		
	}
	
	public ContactTelDetail(String telType, String telNumber) {
		this.telType = telType;
		this.telNumber = telNumber;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	@Column(name="VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name="TEL_TYPE")
	public String getTelType() {
		return telType;
	}

	public void setTelType(String telType) {
		this.telType = telType;
	}

	@Column(name="TEL_NUMBER")
	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	public Contact getContact() {
		return this.contact;
	}
	
	
	public void setContact(Contact contact) {
		this.contact = contact;
		
	}
	
	public String toString() {
		return "Contact Tel Detail - Id: " + id + ", Contact id: " + getContact().getId()
				+ ", Type: " + telType + ", Number" + telNumber;
	}
	
}
