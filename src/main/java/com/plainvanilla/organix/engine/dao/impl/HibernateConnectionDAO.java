package com.plainvanilla.organix.engine.dao.impl;

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
		// TODO Auto-generated constructor stub
	}

}
