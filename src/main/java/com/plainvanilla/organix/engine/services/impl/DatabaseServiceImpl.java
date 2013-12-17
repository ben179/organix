package com.plainvanilla.organix.engine.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plainvanilla.organix.engine.dao.ConfigurationDAO;
import com.plainvanilla.organix.engine.dao.DatabaseDAO;
import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.Database;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.ObjectType;
import com.plainvanilla.organix.engine.model.exception.OrganixIllegalConfigurationException;
import com.plainvanilla.organix.engine.services.DatabaseService;

@Service("databaseService")
public class DatabaseServiceImpl implements DatabaseService {

	private DatabaseDAO databaseDao;
	private ConfigurationDAO configurationDao;

	@Autowired
	public void setDatabaseDao(DatabaseDAO databaseDao) {
		this.databaseDao = databaseDao;
	}

	
	@Autowired
	public void setConfigurationDao(ConfigurationDAO configurationDao) {
		this.configurationDao = configurationDao;
	}


	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ObjectInstance addObjectInstance(ObjectType type, String name, Long dbId) {
		
		Database db = databaseDao.findById(dbId, true);
				
		ObjectInstance instance = new ObjectInstance(name, type);
		db.addObject(instance);
		
		databaseDao.saveOrUpdate(db);
		
		return instance;
	
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ObjectInstance addObjectInstance(Integer typeId, String name, Long dbId) {
		
		Database db = databaseDao.findById(dbId, true);
		ObjectType type = configurationDao.getObjectTypeByTypeId(typeId, db.getConfiguration().getId());
			
		if (type == null) {
			throw new OrganixIllegalConfigurationException("Object type with id " + typeId + " does not exist in the database " + db + " with configuration " + db.getConfiguration());
		}
				
		return addObjectInstance(type, name, dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Connection addConnection(ConnectionType type, ObjectInstance source,
			ObjectInstance target, Long dbId) {
		
		Database db = databaseDao.findById(dbId, true);
		Connection connection = type.createInstance(source, target);
		db.addConnection(connection);
		
		databaseDao.saveOrUpdate(db);
		
		return connection;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Connection addConnection(Integer typeId, ObjectInstance source,
			ObjectInstance target, Long dbId) {

		Database db = databaseDao.findById(dbId, true);
		ConnectionType type = configurationDao.getConnectionTypeByTypeId(typeId, db.getConfiguration().getId());
		
		if (type == null) {
			throw new OrganixIllegalConfigurationException("Connection type with id " + typeId + " does not exist in the database " + db);		
		}
		
		return addConnection(type, source, target, dbId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeConnection(Connection connection, Long dbId) {
		databaseDao.removeConnection(connection.getId(), dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeObjectInstance(ObjectInstance instance, Long dbId) {
		databaseDao.removeObject(instance.getId(), dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ObjectInstance> findObjectsByTypeId(Integer typeId, Long dbId) {
		return databaseDao.getObjectInstanceByTypeId(typeId, dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ObjectInstance> findObjectsByTypeName(String name, Long dbId) {
		return databaseDao.getObjectInstanceByTypeName(name.toLowerCase(), dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Connection> findConnectionsByTypeId(Integer typeId, Long dbId) {
		return databaseDao.getConnectionByTypeId(typeId, dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Connection> findConnectionsByTypeName(String name, Long dbId) {
		return databaseDao.getConnectionByTypeName(name.toLowerCase(), dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ObjectInstance> findObjectsByTypeIdAndName(Integer typeId,
			String name, Long dbId) {
		return databaseDao.getObjectInstanceByTypeIdAndName(typeId, name.toLowerCase(), dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ObjectInstance> findObjectsByName(String name, Long dbId) {
		return databaseDao.getObjectInstanceByName(name.toLowerCase(), dbId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ObjectInstance> getObjectInstances(Long dbId) {
		Database db = databaseDao.findById(dbId, false);
		List<ObjectInstance> objects = new ArrayList<ObjectInstance>(db.getObjects());
		return objects;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Connection> getConnectionInstances(Long dbId) {
		Database db = databaseDao.findById(dbId, false);
		List<Connection> connections = new ArrayList<Connection>(db.getConnections());
		return connections;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeConnectionById(Long id, Long dbId) {
		databaseDao.removeConnection(id, dbId);	
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeObjectInstanceById(Long id, Long dbId) {
		databaseDao.removeObject(id, dbId);
	}
	
}
