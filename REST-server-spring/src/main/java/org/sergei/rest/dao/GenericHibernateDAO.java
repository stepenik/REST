package org.sergei.rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public abstract class GenericHibernateDAO<T extends Serializable> {

    private Class<T> persistentClass;

    public final void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Autowired
    SessionFactory sessionFactory;

    public T findOne(Long aLong) {
        return getCurrentSession().get(persistentClass, aLong);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + persistentClass.getName()).list();
    }

    public void save(T entity) {
        getCurrentSession().save(entity);
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}