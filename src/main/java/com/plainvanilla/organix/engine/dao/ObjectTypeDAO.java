package com.plainvanilla.organix.engine.dao;

import java.util.List;

import com.plainvanilla.organix.engine.model.ObjectType;

public interface ObjectTypeDAO extends GenericDAO<ObjectType, Long>{

	boolean containsObjectTypeId(Integer objectId);
	ObjectType findByTypeId(Integer objectId);
	List<ObjectType> findByName(String name);
	
}
