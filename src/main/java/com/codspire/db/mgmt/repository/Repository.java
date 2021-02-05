package com.codspire.db.mgmt.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public abstract class Repository<T, K> {
	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.codspire.db.mgmt");
	protected EntityManager em;

	abstract Optional<T> save(T obj);

	abstract Optional<T> findById(K key);

	abstract void delete(T obj);

	public void close() {
		emf.close();
	}
}
