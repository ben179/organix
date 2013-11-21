package com.plainvanilla.organix.engine.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.ConnectionTypeDAO;
import com.plainvanilla.organix.engine.model.ConnectionType;

@Repository
public class HibernateConnectionTypeDAO extends AbstractHibernateDAO<ConnectionType, Long> implements ConnectionTypeDAO {

	@Autowired
	public HibernateConnectionTypeDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	
}
