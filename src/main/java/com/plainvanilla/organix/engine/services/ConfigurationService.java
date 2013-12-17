package com.plainvanilla.organix.engine.services;

import java.util.List;

import com.plainvanilla.organix.engine.model.Configuration;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.Database;
import com.plainvanilla.organix.engine.model.ObjectType;

public interface ConfigurationService {
	
	ObjectType addObjectType(Integer id, String name, Long configId);
	ConnectionType addConnectionType(Integer id, String sourceRole, Integer sourceNodeId, boolean sourceUnique, boolean sourceMandatory, String targetRole, Integer targetId, boolean targetUnique, boolean targetMandatory, Long configId);
	
	ObjectType getObjectType(Integer id, Long configId);
	List<ObjectType> getObjectTypeByName(String name, Long configId);
	
	ConnectionType getConnectionType(Integer id, Long configId);
	List<ConnectionType> getConnectionTypeByName(String name, Long configId);
	
	Configuration createNewConfiguration(String name);
	Database createNewDatabase(String dbName, Long configId);
	Configuration getConfiguration(String configName, Integer configVersion);
	Configuration importConfiguration(Configuration configuration);

}
