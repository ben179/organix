package com.plainvanilla.organix.engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORGANIX_DB_USER")
public class User implements OrganixEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
			
	@Column(name="LOGIN", unique=true, nullable=false)
	private String login;
	
	@Column(name="FIRST_NAME", nullable=true)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="DATABASE_ID", nullable=false)
	private Database database;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		
		if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
			
}
