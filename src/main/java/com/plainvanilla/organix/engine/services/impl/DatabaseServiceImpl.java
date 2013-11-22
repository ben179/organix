package com.plainvanilla.organix.engine.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plainvanilla.organix.engine.dao.ConnectionDAO;
import com.plainvanilla.organix.engine.dao.ObjectTypeDAO;
import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.ObjectType;
import com.plainvanilla.organix.engine.services.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService {

	
	private ConnectionDAO connectionDao;
	private ObjectTypeDAO objectTypeDao;
	
	
	@Autowired
	public void setConnectionDao(ConnectionDAO connectionDao) {
		this.connectionDao = connectionDao;
	}
	
	@Autowired
	public void setObjectTypeDao(ObjectTypeDAO objectTypeDao) {
		this.objectTypeDao = objectTypeDao;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ObjectInstance addObjectInstance(ObjectType type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Connection addConnection(ConnectionType type, ObjectInstance source,
			ObjectInstance target) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeObjectInstance(ObjectInstance instance) {
		// TODO Auto-generated method stub
		
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<ObjectInstance> findObjectsByType(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<ObjectInstance> findObjectsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Connection> getAllConnectionsForObject(ObjectInstance instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Connection> getAllIncommingConnections(ObjectInstance instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Connection> getAllOutgoingConnections(ObjectInstance instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Connection> getConnectionsByType(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Connection> getConnectionsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
