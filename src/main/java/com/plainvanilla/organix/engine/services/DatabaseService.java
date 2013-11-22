package com.plainvanilla.organix.engine.services;

import java.util.List;

import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.ObjectType;

public interface DatabaseService {

	ObjectInstance addObjectInstance(ObjectType type, String name);
	Connection addConnection(ConnectionType type, ObjectInstance source, ObjectInstance target);
	void removeConnection(Connection connection);
	void removeObjectInstance(ObjectInstance instance);
		
	List<ObjectInstance> findObjectsByType(Integer typeId);
	List<ObjectInstance> findObjectsByName(String name);
	List<Connection> getAllConnectionsForObject(ObjectInstance instance);
	List<Connection> getAllIncommingConnections(ObjectInstance instance);
	List<Connection> getAllOutgoingConnections(ObjectInstance instance);
		
	List<Connection> getConnectionsByType(Integer typeId);
	List<Connection> getConnectionsByName(String name);	
	
	
	
}
