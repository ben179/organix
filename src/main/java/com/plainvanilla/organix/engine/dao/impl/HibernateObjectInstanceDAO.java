package com.plainvanilla.organix.engine.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.ObjectInstanceDAO;
import com.plainvanilla.organix.engine.model.ObjectInstance;

@Repository
public class HibernateObjectInstanceDAO extends AbstractHibernateDAO<ObjectInstance, Long> implements ObjectInstanceDAO {

	@Autowired
	public HibernateObjectInstanceDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
 
}
