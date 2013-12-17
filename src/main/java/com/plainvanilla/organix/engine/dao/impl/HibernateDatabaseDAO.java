package com.plainvanilla.organix.engine.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.DatabaseDAO;
import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.Database;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.exception.OrganixModelException;

@SuppressWarnings("unchecked")
@Repository
public class HibernateDatabaseDAO extends AbstractHibernateDAO<Database, Long> implements DatabaseDAO  {

	@Autowired
	public HibernateDatabaseDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<ObjectInstance> getObjectInstanceByTypeId(Integer typeId,
			Long dbId) {
		
		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByTypeId");
		q.setParameter("typeId", typeId);
		q.setParameter("dbId", dbId);
		return q.list();
	}

	public List<ObjectInstance> getObjectInstanceByTypeName(String typeName,
			Long dbId) {
		
		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByTypeName");
		q.setParameter("typeName", "%" + typeName + "%");
		q.setParameter("dbId", dbId);				
		return q.list();
	}

	public List<ObjectInstance> getObjectInstanceByTypeIdAndName(
			Integer typeId, String name, Long dbId) {
		
		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByTypeIdAndName");
		q.setParameter("name", "%" + name + "%");
		q.setParameter("typeId", typeId);
		q.setParameter("dbId", dbId);
		return q.list();
	}

	public List<ObjectInstance> getObjectInstanceByName(String name, Long dbId) {

		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByName");
		q.setParameter("name", "%" + name + "%");
		q.setParameter("dbId", dbId);
		return q.list();
		
	}

	public List<Connection> getConnectionByTypeId(Integer typeId, Long dbId) {
		Query q = getCurrentSession().getNamedQuery("findConnectionByTypeId");
		q.setParameter("typeId", typeId);
		q.setParameter("dbId", dbId);
		return q.list();
	}

	public List<Connection> getConnectionByTypeName(String typeName, Long dbId) {
		Query q = getCurrentSession().getNamedQuery("findConnectionByTypeName");
		q.setParameter("typeName", "%" + typeName + "%");
		q.setParameter("dbId", dbId);
		return q.list();
	}

	public void removeObject(Long instanceId, Long dbId) {
		
		Query q = getCurrentSession().getNamedQuery("removeObjectInstance");
		q.setParameter("id", instanceId);
		q.setParameter("dbId", dbId);
		
		int deleted = q.executeUpdate();
		
		if (deleted == 0) {
			throw new OrganixModelException("Object instance with id " + instanceId + " not found in database with id " + dbId);
		}
		
		if (deleted > 1) {
			throw new OrganixModelException(deleted + " instances with id " + instanceId + " not found in database with id " + dbId);
		}
	
	}

	public void removeConnection(Long instanceId, Long dbId) {
		
		Query q = getCurrentSession().getNamedQuery("removeConnectionInstance");
		q.setParameter("id", instanceId);
		q.setParameter("dbId", dbId);
		
		int deleted = q.executeUpdate();
		
		if (deleted == 0) {
			throw new OrganixModelException("Connection instance with id " + instanceId + " not found in database with id " + dbId);
		}
		
		if (deleted > 1) {
			throw new OrganixModelException(deleted + " instances with id " + instanceId + " not found in database with id " + dbId);
		}
	}

}
