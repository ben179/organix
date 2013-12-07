package com.plainvanilla.organix.engine.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class Configuration {

	private Date configurationDate;
	private List<ConnectionType> connectionTypes;
	private List<ObjectType> objectTypes;
	
	
	public Configuration(List<ObjectType> objectTypes, List<ConnectionType> connectionTypes) {
		this.connectionTypes = connectionTypes;
		this.objectTypes = objectTypes;
		this.configurationDate = new Date();
	}
	
	public List<ConnectionType> getConnectionTypes() {
		return Collections.unmodifiableList(connectionTypes);
	}

	public List<ObjectType> getObjectTypes() {
		return Collections.unmodifiableList(objectTypes);
	}

	public Date getConfigurationDate() {
		return configurationDate;
	}
	
}
