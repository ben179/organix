package com.plainvanilla.organix.engine.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plainvanilla.organix.engine.dao.ConnectionDAO;
import com.plainvanilla.organix.engine.dao.ObjectInstanceDAO;
import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.ObjectType;
import com.plainvanilla.organix.engine.model.exception.OrganixIllegalConfigurationException;
import com.plainvanilla.organix.engine.services.DatabaseConfigurationService;
import com.plainvanilla.organix.engine.services.DatabaseService;

@Service("databaseService")
public class DatabaseServiceImpl implements DatabaseService {

	
	private ConnectionDAO connectionDao;
	private ObjectInstanceDAO objectInstanceDao;
	private DatabaseConfigurationService configuration;
	
	@Autowired
	public void setConfiguration(DatabaseConfigurationService configuration) {
		this.configuration = configuration;
	}

	@Autowired
	public void setConnectionDao(ConnectionDAO connectionDao) {
		this.connectionDao = connectionDao;
	}
	
	@Autowired
	public void setObjectInstanceDao(ObjectInstanceDAO objectInstanceDao) {
		this.objectInstanceDao = objectInstanceDao;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ObjectInstance addObjectInstance(ObjectType type, String name) {
		
		ObjectInstance instance = new ObjectInstance(name, type);
		return objectInstanceDao.makePersistent(instance);
	
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ObjectInstance addObjectInstance(Integer typeId, String name) {
		
		ObjectType type = configuration.getObjectType(typeId);
		
		if (type == null) {
			throw new OrganixIllegalConfigurationException("Object type with id " + typeId + " does not exist in the database");
		}
				
		return addObjectInstance(type, name);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Connection addConnection(ConnectionType type, ObjectInstance source,
			ObjectInstance target) {
		Connection connection = type.createConnection(source, target);
		return connectionDao.makePersistent(connection);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Connection addConnection(Integer typeId, ObjectInstance source,
			ObjectInstance target) {

		ConnectionType type = configuration.getConnectionType(typeId);
		
		if (type == null) {
			throw new OrganixIllegalConfigurationException("Connection type with id " + typeId + " does not exist in the database");		
		}
		
		return addConnection(type, source, target);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeConnection(Connection connection) {
		connectionDao.makeTransient(connection);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeObjectInstance(ObjectInstance instance) {
		objectInstanceDao.makeTransient(instance);
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<ObjectInstance> findObjectsByTypeId(Integer typeId) {
		return objectInstanceDao.getObjectInstanceByTypeId(typeId);
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<ObjectInstance> findObjectsByTypeName(String name) {
		return objectInstanceDao.getObjectInstanceByTypeName(name.toLowerCase());
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Connection> findConnectionsByTypeId(Integer typeId) {
		return connectionDao.getConnectionByTypeId(typeId);
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Connection> findConnectionsByTypeName(String name) {
		return connectionDao.getConnectionByTypeName(name.toLowerCase());
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<ObjectInstance> findObjectsByTypeIdAndName(Integer typeId,
			String name) {
		return objectInstanceDao.getObjectInstanceByTypeIdAndName(typeId, name.toLowerCase());
	}

	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<ObjectInstance> findObjectsByName(String name) {
		return objectInstanceDao.getObjectInstanceByName(name.toLowerCase());
	}
	
}
