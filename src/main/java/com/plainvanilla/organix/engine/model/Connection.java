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
@Table(name="CONNECTION")
public final class Connection {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="CONNECTION_ID", insertable=false, updatable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="SOURCE_ID", nullable=false)
	private ObjectInstance sourceObject;
	
	@ManyToOne
	@JoinColumn(name="TARGET_ID", nullable=false)
	private ObjectInstance targetObject;
		
	@ManyToOne
	@JoinColumn(name="TYPE_ID", nullable=false)
	private ConnectionType type;

	public Connection(ObjectInstance source, ObjectInstance target, ConnectionType type) {
		
		this.sourceObject = source;
		this.targetObject = target;
		this.type = type;
	}

	public ObjectInstance getSourceObject() {
		return sourceObject;
	}

	public ObjectInstance getTargetObject() {
		return targetObject;
	}

	public ConnectionType getType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
