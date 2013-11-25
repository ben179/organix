package com.plainvanilla.organix.engine.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.ConnectionTypeDAO;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.exception.OrganixModelException;

@Repository
public class HibernateConnectionTypeDAO extends AbstractHibernateDAO<ConnectionType, Long> implements ConnectionTypeDAO {

	@Autowired
	public HibernateConnectionTypeDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public boolean containsConnectionTypeId(Integer objectId) {
		return (findByTypeId(objectId) != null);
	}

	public ConnectionType findByTypeId(Integer objectId) {
		
		ConnectionType type = new ConnectionType();
		type.setTypeNumber(objectId);
		
		List<ConnectionType> types = super.findByExample(type);
		
		if (types.size() > 1) {
			throw new OrganixModelException("More than one Connection type with type id : " + objectId + " exists in database!");
		}
		
		if (types.isEmpty()) {
			return null;
		}
		
		return types.get(0);
	}

	public List<ConnectionType> findByName(String name) {
		Query q = super.getCurrentSession().createQuery("from ConnectionType c where lower(c.sourceEnd.roleName) like :role or lower(c.targetEnd.roleName) like :role");
		q.setParameter("role", "%" + name + "%");
		return q.list();
	}
	
}
