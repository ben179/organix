package com.plainvanilla.organix.engine.dao;

import java.util.List;

import com.plainvanilla.organix.engine.model.ObjectInstance;

public interface ObjectInstanceDAO extends GenericDAO<ObjectInstance, Long> {

	List<ObjectInstance> getObjectInstanceByTypeId(Integer typeId);
	List<ObjectInstance> getObjectInstanceByTypeName(String typeName);
	List<ObjectInstance> getObjectInstanceByTypeIdAndName(Integer typeId, String name);
	List<ObjectInstance> getObjectInstanceByName(String name);	
}
