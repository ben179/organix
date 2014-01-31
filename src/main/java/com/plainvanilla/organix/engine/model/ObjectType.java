package com.plainvanilla.organix.engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="OBJECT_TYPE")
public class ObjectType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CONFIGURATION_ID", referencedColumnName="ID", nullable=false)
	private Configuration configuration;	
	
	@Column(name="TYPE_ID", nullable=false, unique=false)
	private Integer typeNumber;
		
	@NotBlank(message="Object Type name must be supplied.")
	@Column(name="TYPE_NAME", nullable=false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(Integer typeNumber) {
		this.typeNumber = typeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void copyFrom(ObjectType other) {
		this.name = other.name;
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
		ObjectType other = (ObjectType) obj;
		if (configuration == null) {
			if (other.configuration != null)
				return false;
		} else if (!configuration.equals(other.configuration))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (typeNumber == null) {
			if (other.typeNumber != null)
				return false;
		} else if (!typeNumber.equals(other.typeNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ObjectType [typeNumber=" + typeNumber + ", name=" + name + "]";
	}
	
}
