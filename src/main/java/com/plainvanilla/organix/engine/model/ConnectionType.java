package com.plainvanilla.organix.engine.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.plainvanilla.organix.engine.model.exception.OrganixModelException;

@Entity
@Table(name="CONNECTION_TYPE")
public final class ConnectionType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CONFIGURATION_ID", nullable=false)
	private Configuration configuration;
	
	@Column(name="TYPE_ID", nullable=false, unique=true)
	private Integer typeNumber;
	
	@NotNull(message="Source EndPoint must be set.")
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "roleName", column = @Column(name = "SOURCE_ROLE_NAME")),
		@AttributeOverride(name = "mandatory", column = @Column(name = "SOURCE_MANDATORY")),
		@AttributeOverride(name = "unique", column = @Column(name = "SOURCE_UNIQUE")),
		@AttributeOverride(name = "objectType", column = @Column(name = "SOURCE_TYPE")) })
	private ConnectionEndpoint sourceEnd;
	
	@NotNull(message="Target EndPoint must be set.")
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "roleName", column = @Column(name = "TARGET_ROLE_NAME")),
		@AttributeOverride(name = "mandatory", column = @Column(name = "TARGER_MANDATORY")),
		@AttributeOverride(name = "unique", column = @Column(name = "TARGET_UNIQUE")),
		@AttributeOverride(name = "objectType", column = @Column(name = "TARGET_TYPE")) })
	private ConnectionEndpoint targetEnd;
	
	static ConnectionType createType(int typeId, String srcRoleName, int sourceType, boolean sourceUnique, boolean sourceMandatory, String targRoleName, int targetType, boolean targetUnique, boolean targetMandatory) {
		
		ConnectionEndpoint sep = new ConnectionEndpoint(srcRoleName, sourceMandatory, sourceUnique, sourceType);
		ConnectionEndpoint tep = new ConnectionEndpoint(targRoleName, targetMandatory, targetUnique, targetType);
		
		ConnectionType ct = new ConnectionType();
		ct.setSourceEnd(sep);
		ct.setTargetEnd(tep);
		ct.setTypeNumber(typeId);
		
		return ct;
	}
	
	public Connection createInstance(ObjectInstance sourceObject, ObjectInstance targetObject) {
		
		if (!sourceEnd.getObjectType().equals(sourceObject.getType().getTypeNumber())) {
			throw new OrganixModelException("Connection source end type does not match. Excpected type: " + sourceEnd.getObjectType() + ". Found type: " + sourceObject.getType().getTypeNumber());
		}
		
		if (!targetEnd.getObjectType().equals(targetObject.getType().getTypeNumber())) {
			throw new OrganixModelException("Connection target end type does not match. Excpected type: " + targetEnd.getObjectType() + ". Found type: " + targetObject.getType().getTypeNumber());
		}
		
		if (sourceEnd.getUnique() && containsThisConnectionType(sourceObject.getOutgoingConnections())) {
			throw new OrganixModelException("Role '" + sourceEnd.getRoleName() + "' is set as unique. Connection is already set for " + sourceObject.getType().getName() + " '" + sourceObject.getName() + "'");
		}
	
		if (targetEnd.getUnique() && containsThisConnectionType(targetObject.getIncommingConnections())) {
			throw new OrganixModelException("Role '" + targetEnd.getRoleName() + "' is set as unique. Connection is already set for " + targetObject.getType().getName() + " '" + targetObject.getName() + "'");
		}
						
		Connection c =  new Connection(sourceObject, targetObject, this);
		
		sourceObject.addOutgoingConnection(c);
		targetObject.addIncommingConnection(c);
		
		return c;		
	}
	
	private boolean containsThisConnectionType(List<Connection> connections) {
		for (Connection c : connections) {
			if (c.getType().equals(this)) {
				return true;
			}
		}
		
		return false;
	}

	public Integer getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(Integer typeNumber) {
		this.typeNumber = typeNumber;
	}

	public Long getId() {
		return id;
	}

		
	public void setId(Long id) {
		this.id = id;
	}

	public ConnectionEndpoint getSourceEnd() {
		return sourceEnd;
	}

	public void setSourceEnd(ConnectionEndpoint sourceEnd) {
		this.sourceEnd = sourceEnd;
	}

	public ConnectionEndpoint getTargetEnd() {
		return targetEnd;
	}

	public void setTargetEnd(ConnectionEndpoint targetEnd) {
		this.targetEnd = targetEnd;
	}
	
	
	void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}
	
	public String getSrcRoleName() {
		return getSourceEnd().getRoleName();
	}

	public Boolean getSrcMandatory() {
		return getSourceEnd().getMandatory();
	}

	public Boolean getSrcUnique() {
		return getSourceEnd().getUnique();
	}

	public Integer getSrcObjType() {
		return getSourceEnd().getObjectType();
	}
	
	
	public String getTrgRoleName() {
		return getTargetEnd().getRoleName();
	}
	
	public Boolean getTrgMandatory() {
		return getTargetEnd().getMandatory();
	}

	public Boolean getTrgUnique() {
		return getTargetEnd().getUnique();
	}

	public Integer getTrgObjType() {
		return getTargetEnd().getObjectType();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((configuration == null) ? 0 : configuration.hashCode());
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
		ConnectionType other = (ConnectionType) obj;
		if (configuration == null) {
			if (other.configuration != null)
				return false;
		} else if (!configuration.equals(other.configuration))
			return false;
		if (typeNumber == null) {
			if (other.typeNumber != null)
				return false;
		} else if (!typeNumber.equals(other.typeNumber))
			return false;
		return true;
	}

	
	
}
