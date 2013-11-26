package com.plainvanilla.organix.engine.dao;

import java.util.List;

import com.plainvanilla.organix.engine.model.Connection;

public interface ConnectionDAO extends GenericDAO<Connection, Long> {

	List<Connection> getConnectionByTypeId(Integer typeId);
	List<Connection> getConnectionByTypeName(String typeName);
}
