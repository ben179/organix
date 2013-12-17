package com.plainvanilla.organix.engine.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plainvanilla.organix.engine.dao.ConfigurationDAO;
import com.plainvanilla.organix.engine.model.Configuration;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.Database;
import com.plainvanilla.organix.engine.model.ObjectType;
import com.plainvanilla.organix.engine.model.exception.OrganixIllegalConfigurationException;
import com.plainvanilla.organix.engine.model.exception.OrganixModelException;
import com.plainvanilla.organix.engine.services.ConfigurationService;


@Service("databaseConfigurationService")
public class ConfigurationServiceImpl implements ConfigurationService {
	
	private ConfigurationDAO configurationDao;
	
	@Autowired
	public void setConfigurationDao(ConfigurationDAO configurationDao) {
		this.configurationDao = configurationDao;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ObjectType addObjectType(Integer id, String name, Long configId) {
		
		Configuration config = configurationDao.findById(configId, true);
				
		ObjectType objectType = new ObjectType();
		objectType.setName(name);
		
		if (id == null) {		
			final Integer newTypeId = configurationDao.autodetectObjectTypeId(configId);
			objectType.setTypeNumber(newTypeId);		
		} else {
			
			if (configurationDao.containsObjectTypeId(id, config.getId())) {
				throw new OrganixIllegalConfigurationException("Object type with Id " + id + " already exists in Configuration " + config);
			}
			
			objectType.setTypeNumber(id);			
		}

		config.addObjectType(objectType);
		configurationDao.saveOrUpdate(config);
		
		return objectType;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public ConnectionType addConnectionType(Integer id, String sourceRole,
			Integer sourceNodeId, boolean sourceUnique, boolean sourceMandatory,
			String targetRole, Integer targetId, boolean targetUnique,
			boolean targetMandatory, Long configId) {

		Configuration config = configurationDao.findById(configId, true);
		ConnectionType type = ConnectionType.createType(id, sourceRole, sourceNodeId, sourceUnique, sourceMandatory, targetRole, targetId, targetUnique, targetMandatory);

		if (id == null) {		
			final Integer newTypeId = configurationDao.autodetectConnectionTypeId(configId);
			type.setTypeNumber(newTypeId);		
		} else {
			
			if (configurationDao.containsConnectionTypeId(id, config.getId())) {
				throw new OrganixIllegalConfigurationException("Connection type with Id " + id + " already exists in Configuration " + config);
			}
			
			type.setTypeNumber(id);			
		}		
		
		config.addConnectionType(type);
		configurationDao.saveOrUpdate(config);

		return type;
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ObjectType getObjectType(Integer typeId, Long configId) {
		return configurationDao.getObjectTypeByTypeId(typeId, configId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ObjectType> getObjectTypeByName(String name, Long configId) {
		return configurationDao.getObjectTypeByName(name.toLowerCase(), configId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ConnectionType getConnectionType(Integer typeId, Long configId) {
		return configurationDao.getConnectionTypeByTypeId(typeId, configId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ConnectionType> getConnectionTypeByName(String name, Long configId) {
		return configurationDao.getConnectionTypeByName(name.toLowerCase(), configId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Configuration getConfiguration(String name, Integer version) {
		return configurationDao.getByNameAndVersion(name, version);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Configuration importConfiguration(Configuration configuration) {
		
		Configuration config = configurationDao.getByNameAndVersion(configuration.getName(), configuration.getVersion());
		
		if (config == null) {
			config = new Configuration();
			config.setConfigurationDate(new Date());	
			config.setName(configuration.getName());
			config.setVersion(configuration.getVersion());
		} 
		
		config.setTypes(configuration.getObjectTypes(), configuration.getConnectionTypes());		
		configurationDao.saveOrUpdate(config);
	
		return config;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Configuration createNewConfiguration(String name) {
		Configuration config = new Configuration();
		config.setName(name);
		config.setVersion(1);
		config.setConfigurationDate(new Date());
		configurationDao.saveOrUpdate(config);
		
		return config;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Database createNewDatabase(String dbName, Long configId) {
		
		Configuration config = configurationDao.findById(configId, true);
		Database db = config.createDatabase(dbName);
		
		configurationDao.saveOrUpdate(config);
		
		return db;
	}
}
