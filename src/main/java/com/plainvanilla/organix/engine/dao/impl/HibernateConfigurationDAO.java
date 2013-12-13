package com.plainvanilla.organix.engine.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.ConfigurationDAO;
import com.plainvanilla.organix.engine.model.Configuration;
import com.plainvanilla.organix.engine.model.Connection;

@Repository
public class HibernateConfigurationDAO extends AbstractHibernateDAO<Connection, Long> implements ConfigurationDAO { 

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

	@SuppressWarnings("unchecked")
	public List<Configuration> getByName(String name) {
		Query q = super.getCurrentSession().getNamedQuery("findConfigurationByName");
		q.setParameter("name", name);
		return q.list();
	}
	
	

}
