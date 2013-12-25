package com.plainvanilla.organix.engine.dao;

import java.util.List;

import com.plainvanilla.organix.engine.model.Configuration;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectType;

public interface ConfigurationDAO extends GenericDAO<Configuration, Long> {
	
	Configuration getByNameAndVersion(String name, Integer version);
	List<Configuration> getByName(String name);
	Boolean configurationExists(String name, Integer version);
	List<Configuration> getAllConfigurations(boolean headers);

	boolean containsConnectionTypeId(Integer objectId, Long configId);
	ConnectionType getConnectionTypeByTypeId(Integer objectId, Long configId);
	List<ConnectionType> getConnectionTypeByName(String name, Long configId);
	List<ConnectionType> getAllConnectionTypes(Long configId);
	
	boolean containsObjectTypeId(Integer objectId, Long configId);
	ObjectType getObjectTypeByTypeId(Integer objectId, Long configId);
	List<ObjectType> getObjectTypeByName(String name, Long configId);
	List<ObjectType> getAllObjectTypes(Long configId);
	
	Integer autodetectObjectTypeId(Long configId);
	Integer autodetectConnectionTypeId(Long configId);
	
	int removeAllConnectionTypes(Long configId);
	int removeAllObjectTypes(Long configId);

}
