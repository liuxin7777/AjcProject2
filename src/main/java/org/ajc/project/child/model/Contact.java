package org.ajc.project.child.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
	private List<Child> children = new ArrayList<>();

	private String fname;
	private String lname;
	private String email;
	private String contactType;
	private int age;

	public Contact() {
		super();
	}

	/**
	 * @param id
	 * @param fname
	 * @param lname
	 * @param email
	 * @param contactType
	 * @param age
	 */
	public Contact(String fname, String lname, String email, String contactType, int age) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contactType = contactType;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public void addChild(Child child) {
		child.setContact(this);
		this.children.add(child);
	}
}
