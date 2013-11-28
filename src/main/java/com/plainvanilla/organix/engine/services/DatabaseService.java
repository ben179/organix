package com.plainvanilla.organix.engine.services;

import java.util.List;

import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.ObjectType;

public interface DatabaseService {

	ObjectInstance addObjectInstance(ObjectType type, String name);
	ObjectInstance addObjectInstance(Integer typeId, String name);
	
	Connection addConnection(ConnectionType type, ObjectInstance source, ObjectInstance target);
	Connection addConnection(Integer typeId, ObjectInstance source, ObjectInstance target);
	
	void removeConnection(Connection connection);
	void removeObjectInstance(ObjectInstance instance);
		
	List<ObjectInstance> findObjectsByTypeId(Integer typeId);
	List<ObjectInstance> findObjectsByTypeName(String typeName);
	List<ObjectInstance> findObjectsByTypeIdAndName(Integer typeId, String name);
	List<ObjectInstance> findObjectsByName(String name);
	
	List<Connection> findConnectionsByTypeId(Integer typeId);
	List<Connection> findConnectionsByTypeName(String name);	
	
}
