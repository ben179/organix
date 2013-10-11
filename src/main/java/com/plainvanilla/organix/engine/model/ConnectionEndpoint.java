package com.plainvanilla.organix.engine.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConnectionEndpoint {
	
	@Column(name="MANDATORY", nullable=false)
	private Boolean mandatory;
	
	@Column(name="UNIQUE")
	private Boolean unique;
	
	@Column(name="OBJECT_TYPE")
	private Integer objectType;
	
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
