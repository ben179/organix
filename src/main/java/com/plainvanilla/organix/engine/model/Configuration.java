package com.plainvanilla.organix.engine.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.plainvanilla.organix.engine.model.exception.OrganixModelException;


@Entity
@Table(name="CONFIGURATION")
public final class Configuration implements OrganixEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONFIG_DATE")
	private Date configurationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_DATE")
	private Date lastUpdateDate;
	
	@Column(name="NAME", unique=true, nullable=false)
	private String name;
	
	@Column(name="VERSION", nullable=false)
	private Integer version;

	@OneToMany(mappedBy="configuration", fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	private Set<ConnectionType> connectionTypes = new HashSet<ConnectionType>();
	
	@OneToMany(mappedBy="configuration", fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	private Set<ObjectType> objectTypes = new HashSet<ObjectType>();
	
	@OneToMany(mappedBy="configuration", fetch = FetchType.EAGER)	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<Database> databases = new HashSet<Database>();
	
	public Long getId() {
		return id;
	}

	public Configuration() {
	}
	
	public Configuration(Set<ObjectType> objectTypes, Set<ConnectionType> connectionTypes) {
		setTypes(objectTypes, connectionTypes);
	}
	
	public void setTypes(Set<ObjectType> objectTypes, Set<ConnectionType> connectionTypes) {
		
		clearAllTypes();
		
		for (ObjectType objectType : objectTypes) {
			addObjectType(objectType);
		}
		
		for (ConnectionType connectionType : connectionTypes) {
			addConnectionType(connectionType);
		}
		
		this.lastUpdateDate = new Date();
	}
	
	public void clearAllTypes() {
		this.connectionTypes.clear();
		this.objectTypes.clear();
		
		this.lastUpdateDate = new Date();
	}
	
	public ConnectionType addConnectionType(ConnectionType t) {
		return addConnectionType(t.getTypeNumber(), t.getSrcRoleName(), t.getSrcObjType(), t.getSrcUnique(), t.getSrcMandatory(), t.getTrgRoleName(), t.getTrgObjType(), t.getTrgUnique(), t.getTrgMandatory());
	}
	
	public ConnectionType addConnectionType(int typeId, String srcRoleName, int sourceType, boolean sourceUnique, boolean sourceMandatory, String targRoleName, int targetType, boolean targetUnique, boolean targetMandatory) {
		
		ConnectionType type = ConnectionType.createType(typeId, srcRoleName, sourceType, sourceUnique, sourceMandatory, targRoleName, targetType, targetUnique, targetMandatory);
		
		if (!containsObjectType(sourceType)) {
			throw new OrganixModelException("Object type with Id " + type.getTypeNumber() + " does not exist in Configuration " + this.getId());			
		}

		if (!containsObjectType(targetType)) {
			throw new OrganixModelException("Object type with Id " + type.getTypeNumber() + " does not exist in Configuration " + this.getId());
		}
		
		type.setConfiguration(this);
		connectionTypes.add(type);
		
		this.lastUpdateDate = new Date();
		
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
		
		objectTypes.add(type);
		
		this.lastUpdateDate = new Date();
		
		return type;
	}
	
	

	public Set<Database> getDatabases() {
		return Collections.unmodifiableSet(databases);
	}


	public Database createDatabase(String name) {
		
		Database d = new Database();
		
		d.setConfiguration(this);
		d.setName(name);
		
		if (databases.contains(d)) {
			throw new OrganixModelException("Database " + d + " already exists for configuration " + this);
		}
		
		databases.add(d);
		
		return d;
	}

	public void removeDatabase(Database d) {
		d.cleanDatabase();
		d.setConfiguration(null);
		databases.remove(d);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
		
	public void setConfigurationDate(Date configurationDate) {
		this.configurationDate = configurationDate;
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
	
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public boolean containsConnectionType(ConnectionType type) {
		return connectionTypes.contains(type);
	}

	public boolean containsObjectType(ObjectType type) {
		return objectTypes.contains(type);
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

	@Override
	public String toString() {
		return "Configuration [name=" + name + ", version=" + version + "]";
	}
	
}
