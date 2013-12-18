package com.plainvanilla.organix.engine.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.ConfigurationDAO;
import com.plainvanilla.organix.engine.model.Configuration;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectType;

@SuppressWarnings("unchecked")
@Repository
public class HibernateConfigurationDAO extends AbstractHibernateDAO<Configuration, Long> implements ConfigurationDAO { 

	@Autowired
	public HibernateConfigurationDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Configuration getByNameAndVersion(String name, Integer version) {
		
		Query q = super.getCurrentSession().getNamedQuery("findConfigurationByNameAndVersion");
		q.setParameter("name", name);
		q.setParameter("version", version);
		return (Configuration)q.uniqueResult();
	}

	public Boolean configurationExists(String name, Integer version) {
		Configuration c = getByNameAndVersion(name, version);
		return (c != null);
	}

	public List<Configuration> getByName(String name) {
		Query q = super.getCurrentSession().getNamedQuery("findConfigurationByName");
		q.setParameter("name", name);
		return q.list();
	}

	public ConnectionType getConnectionTypeByTypeId(Integer typeId, Long configId) {
		
		Query q = super.getCurrentSession().getNamedQuery("findConnectionTypeByTypeId");
		q.setParameter("typeId", typeId);
		q.setParameter("configId", configId);

		return (ConnectionType)q.uniqueResult();
	}

	public boolean containsConnectionTypeId(Integer objectId, Long configId) {
		return (getConnectionTypeByTypeId(objectId,configId) != null);
	}

	public List<ConnectionType> getConnectionTypeByName(String name,
			Long configId) {

		Query q = super.getCurrentSession().getNamedQuery("findConnectionTypeByName");
		q.setParameter("role", "%" + name + "%");
		q.setParameter("configId", configId);
		return q.list();
		
	}

	public boolean containsObjectTypeId(Integer typeId, Long configId) {
		return (getObjectTypeByTypeId(typeId, configId) != null);
	}

	public ObjectType getObjectTypeByTypeId(Integer typeId, Long configId) {
		
		Query q = super.getCurrentSession().getNamedQuery("findObjectTypeByTypeId");
		q.setParameter("typeId", typeId);
		q.setParameter("configId", configId);

		return (ObjectType)q.uniqueResult();
	}

	public List<ObjectType> getObjectTypeByName(String name, Long configId) {
		
		Query q = super.getCurrentSession().getNamedQuery("findObjectTypeByName");
		q.setParameter("name", "%" + name + "%");
		q.setParameter("configId", configId);
		return q.list();
	}

	public int removeAllConnectionTypes(Long configId) {
		Query q = super.getCurrentSession().getNamedQuery("removeAllConnectionTypes");
		q.setParameter("configId", configId);
		return q.executeUpdate();
	}

	public int removeAllObjectTypes(Long configId) {
		Query q = super.getCurrentSession().getNamedQuery("removeAllObjectTypes");
		q.setParameter("configId", configId);
		return q.executeUpdate();
	}

	public Integer autodetectObjectTypeId(Long configId) {
		
		Query q = super.getCurrentSession().getNamedQuery("findMaxObjectTypeId");
		q.setParameter("configId", configId);
		
		Integer maxTypeId = (Integer)q.uniqueResult();
		
		if (maxTypeId == null) {
			maxTypeId = 0;
		}
		
		return ++maxTypeId;		
	}

	public Integer autodetectConnectionTypeId(Long configId) {
		
		Query q = super.getCurrentSession().getNamedQuery("findMaxConnectionTypeId");
		q.setParameter("configId", configId);
		
		Integer maxTypeId = (Integer)q.uniqueResult();
		
		if (maxTypeId == null) {
			maxTypeId = 0;
		}
		
		return ++maxTypeId;	
	}

	public List<ConnectionType> getAllConnectionTypes(Long configId) {
		Query q = super.getCurrentSession().getNamedQuery("findConnectionTypes");
		q.setParameter("configId", configId);
		return q.list();
	}

	public List<ObjectType> getAllObjectTypes(Long configId) {
		Query q = super.getCurrentSession().getNamedQuery("findObjectTypes");
		q.setParameter("configId", configId);
		return q.list();
	}
	

}
