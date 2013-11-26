package com.plainvanilla.organix.engine.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.ConnectionDAO;
import com.plainvanilla.organix.engine.model.Connection;

@Repository
public class HibernateConnectionDAO extends AbstractHibernateDAO<Connection, Long> implements ConnectionDAO {

	@Autowired
	public HibernateConnectionDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Connection> getConnectionByTypeId(Integer typeId) {
		
		Query q = getCurrentSession().getNamedQuery("findConnectionByTypeId");
		q.setParameter("typeId", typeId);		
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Connection> getConnectionByTypeName(String typeName) {
		
		Query q = getCurrentSession().getNamedQuery("findConnectionByTypeName");
		q.setParameter("typeName", "%" + typeName + "%");
		return q.list();
	}

}
