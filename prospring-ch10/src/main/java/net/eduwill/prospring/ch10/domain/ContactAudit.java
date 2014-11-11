package net.eduwill.prospring.ch10.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

@Entity
@Audited
@Table(name="contact_audit")
public class ContactAudit implements Auditable<String, Long>, Serializable{
	
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int version;
	private String createdBy;
	private DateTime createdDate;
	private String lastModifiedBy;
	private DateTime lastModifiedDate;
	private Set<Hobby> hobbies  = new HashSet<Hobby>();
	private Set<ContactTelDetail> contactTelDetails  = new HashSet<ContactTelDetail>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
	
	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="LAST_MODIFIED_BY")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	@Column(name="LAST_MODIFIED_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Transient
	public boolean isNew() {
		if(id == null) {
			return true;
		} else {
			return false;
		}
		
	}

	@ManyToMany
	@NotAudited
	@JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "CONTACT_ID"), inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
	public Set<Hobby> getHobbies() {
		return this.hobbies;
	}
	
	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	@OneToMany(mappedBy="contact", cascade=CascadeType.ALL, orphanRemoval=true)
	@NotAudited
	public Set<ContactTelDetail> getContactTelDetails() {
		return this.contactTelDetails;
	}
	
	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		
		this.contactTelDetails = contactTelDetails;
	}
	
	
	public String toString() {
		return "Contact - Id : " + id
				+ ", First name: " + firstName
				+ ", Last name: " + lastName
				+ ", Birthday: " + birthDate
				+ ", Created By: " + createdBy
				+ ", Create date: " + createdDate
				+ ", Modified By: " + lastModifiedBy
				+ ", Modified date: " + lastModifiedDate;
	}
	
	
}