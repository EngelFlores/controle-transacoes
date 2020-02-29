package com.acelera.tcc.group03.repositories;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.acelera.tcc.group03.domains.BaseEntity;

public abstract class DAO<T extends BaseEntity> {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	protected abstract String getSqlString();
	
	protected abstract Class<T> getClassName();
	
	private void begin() {
		this.emf = Persistence.createEntityManagerFactory("transaction-system-pu");
		this.em = this.emf.createEntityManager();
		this.em.getTransaction().begin();
	}
	
	private void close() {
		if (Objects.nonNull(this.em)) {
			if (this.em.getTransaction().isActive()) {
				this.em.getTransaction().commit();
			}
			
			this.em.close();
		}
		
		if (Objects.nonNull(this.emf)) {
			this.emf.close();
		}
	}
	
	public void create(T domain) {
		this.begin();
		this.em.persist(domain);
		this.close();
	}
	
	public void update(T domain) {
		this.begin();
		this.em.persist(domain);
		this.close();
	}
	
	public void delete(T domain) {
		this.begin();
		this.em.remove(domain);
		this.close();
	}
	
	public List<T> readAll() {
		this.begin();
		Query query = this.em.createQuery(this.getSqlString());
		List<T> resultList = query.getResultList();
		this.close();
		return resultList;
	}
	
	public T findById(Long id) {
		this.begin();
		T value = (T) this.em.find(this.getClassName(), id);
		this.close();
		
		return value;
	}
}