package com.plainvanilla.organix.engine.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.plainvanilla.organix.engine.model.exception.OrganixModelException;

@Entity
@Table(name="ORGANIX_DB")
public class Database {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="NAME", nullable=false, unique=true)	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="CONFIG_ID", nullable=false)
	private Configuration configuration;
	
	@OneToMany(mappedBy="database", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<ObjectInstance> objects = new HashSet<ObjectInstance>();
	
	@OneToMany(mappedBy="database", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<Connection> connections = new HashSet<Connection>();

	@OneToMany(mappedBy="database", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<User> users = new HashSet<User>();
	
	public boolean verify() {
		
		// TODO : verify DB against its configuration
		return true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Set<ObjectInstance> getObjects() {
		return Collections.unmodifiableSet(objects);
	}

	public void addObject(ObjectInstance o) {
		
		if (!configuration.containsObjectType(o.getType())) {
			throw new OrganixModelException("Configuration " + configuration + " does not contain connection type " + o.getType());
		}
		
		o.setDatabase(this);
		objects.add(o);
	}
	
	public void removeObject(ObjectInstance o) {
		objects.remove(o);
		o.setDatabase(null);
	}
	
	public void cleanDatabase() {
		removeAllConnections();
		
		for (ObjectInstance i : objects) {
			i.setDatabase(null);
		}
		
		objects.clear();
	}

	public Set<Connection> getConnections() {
		return Collections.unmodifiableSet(connections);
	}
	
	public void addConnection(Connection c) {
		
		if (!configuration.containsConnectionType(c.getType())) {
			throw new OrganixModelException("Configuration " + configuration + " does not contain connection type " + c.getType());
		}
		
		c.setDatabase(this);
		connections.add(c);
	}

	public void removeConnection(Connection c) {
		connections.remove(c);
		c.setDatabase(null);
	}
	
	public void removeAllConnections() {
		for (Connection c : connections) {
			c.setDatabase(null);
		}
		connections.clear();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return Collections.unmodifiableSet(users);
	}

	public void addUser(User u) {
		users.add(u);
		u.setDatabase(this);		
	}
	
	public void removeUser(User u) {
		users.remove(u);
		u.setDatabase(null);
	}
	
	public void removeAllUsers() {
		
		for (User u : users) {
			u.setDatabase(null);
		}
		
		users.clear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((configuration == null) ? 0 : configuration.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Database other = (Database) obj;
		
		if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Database [name=" + name + "]";
	}

}
