package net.eduwill.prospring.ch10.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="contact")
@NamedQueries(
		{
			@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c"),
			@NamedQuery(name="Contact.findAllWithDetail", 
					query="SELECT DISTINCT c FROM Contact c LEFT JOIN FETCH c.contactTelDetails t LEFT JOIN FETCH c.hobbies h"),
			@NamedQuery(name="Contact.findById", 
					query="SELECT DISTINCT c FROM Contact c LEFT JOIN FETCH c.contactTelDetails t LEFT JOIN FETCH c.hobbies h WHERE c.id=:id")
		}
		)
@SqlResultSetMapping(name="contactResult",
	entities=@EntityResult(entityClass=Contact.class)
)
public class Contact implements Serializable {
	
	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private Date birthDate;
	
	private Set<ContactTelDetail> contactTelDetails = new HashSet<ContactTelDetail>();
	private Set<Hobby> hobbies = new HashSet<Hobby>();
	
	@OneToMany(mappedBy="contact", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<ContactTelDetail> getContactTelDetails() {
		return this.contactTelDetails;
	}
	
	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		
		this.contactTelDetails = contactTelDetails;
	}
	
	public void addContactTelDetail(ContactTelDetail contactTelDetail) {
		contactTelDetail.setContact(this);
		getContactTelDetails().add(contactTelDetail);
	}
	
	public void removeContactTelDetail(ContactTelDetail contactTelDetail) {
		getContactTelDetails().remove(contactTelDetail);
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@ManyToMany
	@JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "CONTACT_ID"), inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
	public Set<Hobby> getHobbies() {
		return this.hobbies;
	}
	
	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	public String toString() {
		return "Contact - Id: " + id + " , First name: " + firstName
				+ ", Last name: " + lastName + " , Birthday: " + birthDate;
	}
	
	
}
