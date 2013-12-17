package com.plainvanilla.organix.engine.services;

import java.util.List;

import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.ObjectType;

public interface DatabaseService {

	ObjectInstance addObjectInstance(ObjectType type, String name, Long dbId);
	ObjectInstance addObjectInstance(Integer typeId, String name, Long dbId);
	
	Connection addConnection(ConnectionType type, ObjectInstance source, ObjectInstance target, Long dbId);
	Connection addConnection(Integer typeId, ObjectInstance source, ObjectInstance target, Long dbId);
	
	void removeConnection(Connection connection, Long dbId);
	void removeConnectionById(Long id, Long dbId);
	void removeObjectInstance(ObjectInstance instance, Long dbId);
	void removeObjectInstanceById(Long id, Long dbId);	
	
	List<ObjectInstance> getObjectInstances(Long dbId);
	List<ObjectInstance> findObjectsByTypeId(Integer typeId, Long dbId);
	List<ObjectInstance> findObjectsByTypeName(String typeName, Long dbId);
	List<ObjectInstance> findObjectsByTypeIdAndName(Integer typeId, String name, Long dbId);
	List<ObjectInstance> findObjectsByName(String name, Long dbId);
	
	List<Connection> getConnectionInstances(Long dbId);
	List<Connection> findConnectionsByTypeId(Integer typeId, Long dbId);
	List<Connection> findConnectionsByTypeName(String name, Long dbId);	
	
}
