package com.plainvanilla.organix.engine.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.plainvanilla.organix.engine.model.exception.OrganixModelException;


@Entity
@Table(name="CONFIGURATION")
public final class Configuration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONFIG_DATE")
	private Date configurationDate;
	
	@Column(name="NAME", unique=true, nullable=false)
	private String name;
	
	@Column(name="VERSION", nullable=false)
	private Integer version;
	
	@OneToMany(mappedBy="configuration")
	private Set<ConnectionType> connectionTypes = new HashSet<ConnectionType>();
	
	@OneToMany(mappedBy="configuration")
	private Set<ObjectType> objectTypes = new HashSet<ObjectType>();
	
	public Long getId() {
		return id;
	}
	
	public Configuration(Set<ObjectType> objectTypes, Set<ConnectionType> connectionTypes) {
		this.connectionTypes = connectionTypes;
		this.objectTypes = objectTypes;
		this.configurationDate = new Date();
	}
	
	public ConnectionType addConnectionType(ConnectionType t) {
		return addConnectionType(t.getTypeNumber(), t.getSrcRoleName(), t.getSrcObjType(), t.getSrcUnique(), t.getSrcMandatory(), t.getTrgRoleName(), t.getTrgObjType(), t.getTrgUnique(), t.getTrgMandatory());
	}
	
	public ConnectionType addConnectionType(int typeId, String srcRoleName, int sourceType, boolean sourceUnique, boolean sourceMandatory, String targRoleName, int targetType, boolean targetUnique, boolean targetMandatory) {
		
		ConnectionType type = ConnectionType.createType(typeId, srcRoleName, sourceType, sourceUnique, sourceMandatory, targRoleName, targetType, targetUnique, targetMandatory);
	
		if (connectionTypes.contains(type)) {
			throw new OrganixModelException("Connection type with Id " + type.getTypeNumber() + " already exists in Configuration " + this.getId());
		}
		
		if (!containsObjectType(sourceType)) {
			throw new OrganixModelException("Object type with Id " + type.getTypeNumber() + " does not exist in Configuration " + this.getId());			
		}

		if (!containsObjectType(targetType)) {
			throw new OrganixModelException("Object type with Id " + type.getTypeNumber() + " does not exist in Configuration " + this.getId());
		}
		
		type.setConfiguration(this);
		connectionTypes.add(type);
		
		return type;
	}
	
	public ObjectType addObjectType(ObjectType type) {
		return addObjectType(type.getTypeNumber(), type.getName());
	}
	
	public ObjectType addObjectType(Integer id, String name) {
		
		ObjectType type = new ObjectType();
		type.setTypeNumber(id);
		type.setName(name);
		type.setConfiguration(this);
		
		if (objectTypes.contains(type)) {
			throw new OrganixModelException("Object type with Id " + type.getTypeNumber() + " already exists in Configuration " + this.getId());
		}
		
		objectTypes.add(type);
		
		return type;
	}
	
	public Set<ConnectionType> getConnectionTypes() {
		return Collections.unmodifiableSet(connectionTypes);
	}

	public Set<ObjectType> getObjectTypes() {
		return Collections.unmodifiableSet(objectTypes);
	}

	public Date getConfigurationDate() {
		return configurationDate;
	}
	
	private boolean containsObjectType(Integer typeNumber) {
		for (ObjectType type : objectTypes) {
			if (type.getTypeNumber().equals(typeNumber)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		Configuration other = (Configuration) obj;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		
		return true;
	}

	
	
}
