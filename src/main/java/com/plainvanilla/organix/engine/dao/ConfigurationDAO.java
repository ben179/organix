package com.plainvanilla.organix.engine.dao;

import java.util.List;

import com.plainvanilla.organix.engine.model.Configuration;
import com.plainvanilla.organix.engine.model.Connection;

public interface ConfigurationDAO extends GenericDAO<Connection, Long> {
	
	Configuration getByNameAndVersion(String name, Integer version);
	List<Configuration> getByName(String name);
	Boolean configurationExists(String name, Integer version);
	

}
