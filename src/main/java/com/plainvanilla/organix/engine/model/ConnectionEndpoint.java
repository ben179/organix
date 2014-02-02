package com.plainvanilla.organix.engine.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class ConnectionEndpoint implements OrganixEntity {
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="MANDATORY", nullable=false)
	private Boolean mandatory = Boolean.FALSE;

	@Column(name="UNIQUE")
	private Boolean unique = Boolean.FALSE;
	
	@NotNull(message="Endpoint Object type must be set.")
	@Column(name="OBJECT_TYPE")
	private Integer objectType;
	
	@NotBlank(message="Endpoint role name must be set.")
	@Column(name="ROLE_NAME")
	private String roleName;

	public ConnectionEndpoint() {
		
	}
	
	public ConnectionEndpoint(String roleName, Boolean mandatory, Boolean unique, Integer objectType) {
		super();
		this.mandatory = mandatory;
		this.unique = unique;
		this.objectType = objectType;
		this.roleName = roleName;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getRoleName() {
		return roleName;
	}

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	
	
	
	
}
