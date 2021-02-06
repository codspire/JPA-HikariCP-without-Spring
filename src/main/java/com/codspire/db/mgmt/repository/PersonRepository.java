package com.codspire.db.mgmt.repository;

import com.codspire.db.mgmt.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;
import java.util.Optional;

public class PersonRepository extends Repository<Person, Long> {

	@Override
	public Optional<Person> save(Person person) {
		Objects.requireNonNull(person, "Person must not be null");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			if (person.getId() != null) {
				em.merge(person);
			} else {
				em.persist(person);
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // or display error message
		} finally {
			em.close();
		}
		return Optional.of(person);
	}

	@Override
	public Optional<Person> findById(Long key) {
		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();

		Person person = em.find(Person.class, key);
//		em.getTransaction().commit();
		return Optional.ofNullable(person);
	}

	@Override
	public void delete(Person person) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.contains(person) ? person : em.merge(person));

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // or display error message
		} finally {
			em.close();
		}
	}
}
