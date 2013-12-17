package com.plainvanilla.organix.engine.dao;

import java.util.List;

import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.Database;
import com.plainvanilla.organix.engine.model.ObjectInstance;

public interface DatabaseDAO extends GenericDAO<Database, Long> {

	List<ObjectInstance> getObjectInstanceByTypeId(Integer typeId, Long dbId);
	List<ObjectInstance> getObjectInstanceByTypeName(String typeName, Long dbId);
	List<ObjectInstance> getObjectInstanceByTypeIdAndName(Integer typeId, String name, Long dbId);
	List<ObjectInstance> getObjectInstanceByName(String name, Long dbId);	
	void removeObject(Long instanceId, Long dbId);
	
	List<Connection> getConnectionByTypeId(Integer typeId, Long dbId);
	List<Connection> getConnectionByTypeName(String typeName, Long dbId);
	void removeConnection(Long instanceId, Long dbId);
}
