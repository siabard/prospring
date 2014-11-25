package net.eduwill.prospring.ch17.domain;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="contact")
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8130549790274575962L;
	
	
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", version=" + version + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", description=" + description  + "]";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@Transient
	public String getBirthDateString() {
		String birthDateString = "";
		if (this.birthDate != null)  {
			birthDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(this.birthDate);
			
		}
		
		return birthDateString;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column(name="PHOTO")
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private DateTime birthDate;
	private String description;
	private byte[] photo;
	
	
	

}
