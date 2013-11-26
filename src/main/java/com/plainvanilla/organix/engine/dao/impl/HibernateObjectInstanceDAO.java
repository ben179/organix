package com.plainvanilla.organix.engine.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
	}

	@SuppressWarnings("unchecked")
	public List<ObjectInstance> getObjectInstanceByTypeId(Integer typeId) {
		
		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByTypeId");
		q.setParameter("typeId", typeId);
		return q.list();
	}

	
	@SuppressWarnings("unchecked")
	public List<ObjectInstance> getObjectInstanceByTypeName(String typeName) {
		
		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByTypeName");
		q.setParameter("typeName", "%" + typeName + "%");
				
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<ObjectInstance> getObjectInstanceByTypeIdAndName(
			Integer typeId, String name) {
		
		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByTypeIdAndName");
		q.setParameter("name", "%" + name + "%");
		q.setParameter("typeId", typeId);
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<ObjectInstance> getObjectInstanceByName(String name) {

		Query q = getCurrentSession().getNamedQuery("findObjectInstanceByName");
		q.setParameter("name", "%" + name + "%");
		
		return q.list();
	
	}
 
}
