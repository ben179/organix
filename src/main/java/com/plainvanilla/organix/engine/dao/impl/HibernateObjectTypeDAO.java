package com.plainvanilla.organix.engine.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.ObjectTypeDAO;
import com.plainvanilla.organix.engine.model.ObjectType;

@Repository
public class HibernateObjectTypeDAO extends AbstractHibernateDAO<ObjectType, Long> implements ObjectTypeDAO {

	@Autowired
	public HibernateObjectTypeDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

}
