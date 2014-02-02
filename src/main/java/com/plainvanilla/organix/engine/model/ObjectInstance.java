package com.plainvanilla.organix.engine.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OBJECT_INSTANCE")
public class ObjectInstance implements OrganixEntity {
		
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="OBJECT_ID")
	private Long id;	
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="TYPE_ID")
	private ObjectType type;
	
	@OneToMany(mappedBy="sourceObject")	
	protected List<Connection> incommingConnections = new ArrayList<Connection>();

	@OneToMany(mappedBy="targetObject")	
	protected List<Connection> outgoingConnections = new ArrayList<Connection>();

	@ManyToOne
	@JoinColumn(name="DATABASE_ID", nullable=false)
	private Database database;
	
	public ObjectInstance() {
		
	}
	
	public ObjectInstance(String name, ObjectType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public List<Connection> getIncommingConnections() {
		return Collections.unmodifiableList(incommingConnections);
	}

	public void addIncommingConnection(Connection c) {
		incommingConnections.add(c);		
	}
	
	public void removeIncommingConnection(Connection c) {
		incommingConnections.remove(c);		
	}	
	
	public List<Connection> getOutgoingConnections() {
		return Collections.unmodifiableList(outgoingConnections);
	}

	public void addOutgoingConnection(Connection c) {
		outgoingConnections.add(c);		
	}
	
	public void removeOutgoingConnection(Connection c) {
		outgoingConnections.remove(c);		
	}	
	
	public ObjectType getType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	@Override
	public String toString() {
		return "ObjectInstance [name=" + name + ", type=" + type + "]";
	}
		
}
