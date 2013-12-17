package com.plainvanilla.organix.engine.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plainvanilla.organix.engine.dao.GenericDAO;

@Repository
@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	private Class<T> persistentClass;
	private SessionFactory sessionFactory;
	
	@Autowired
	public AbstractHibernateDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	protected Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	
	public T findById(ID id, boolean lock) {
		T entity;
		
		if (lock) {
			entity = (T) getCurrentSession().load(getPersistentClass(), id, LockMode.UPGRADE);
		} else {
			entity = (T) getCurrentSession().load(getPersistentClass(), id);
		}
		
		return entity;
	}
	
	public List<T> findAll() {
		Criteria c = getCurrentSession().createCriteria(getPersistentClass());
		return c.list();
	}
	
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Example example = Example.create(exampleInstance).ignoreCase().enableLike(MatchMode.ANYWHERE);
		
		for (String property : excludeProperty) {
			example.excludeProperty(property);
		}
				
		return getCurrentSession().createCriteria(getPersistentClass()).add(example).list();
		
	}
	
	public T makePersistent(T entity) {
		return (T)getCurrentSession().merge(entity);
	}
	
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}
		
	public void makeTransient(T entity) {
		getCurrentSession().delete(entity);
	}
	
	
	public void flush() {
		getCurrentSession().flush();
	}
	
	public void clear() {
		getCurrentSession().clear();
	}
}
