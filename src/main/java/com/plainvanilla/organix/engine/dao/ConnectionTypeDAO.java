package com.plainvanilla.organix.engine.dao;

import java.util.List;

import com.plainvanilla.organix.engine.model.ConnectionType;

public interface ConnectionTypeDAO extends GenericDAO<ConnectionType, Long> {

	boolean containsConnectionTypeId(Integer objectId);
	ConnectionType findByTypeId(Integer objectId);
	List<ConnectionType> findByName(String name);

	Integer autodetectFreeTypeId();
}
