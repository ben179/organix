package com.plainvanilla.organix.engine.services;

import java.util.List;

import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectType;

public interface DatabaseConfigurationService {

	ObjectType addObjectType(Integer id, String name);
	ConnectionType addConnectionType(Integer id, String sourceRole, Integer sourceNodeId, boolean sourceUnique, boolean sourceMandatory, String targetRole, Integer targetId, boolean targetUnique, boolean targetMandatory);
	
	ObjectType getObjectType(Integer id);
	List<ObjectType> getObjectTypeByName(String name);
	
	ConnectionType getConnectionType(Integer id);
	List<ConnectionType> getConnectionTypeByName(String name);
	
	List<ConnectionType> getConnectionTypes();
	List<ObjectType> getObjectTypes();
		
}
