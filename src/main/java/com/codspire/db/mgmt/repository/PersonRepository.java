package com.codspire.db.mgmt.repository;

import com.codspire.db.mgmt.entity.Person;

import java.util.Objects;
import java.util.Optional;

public class PersonRepository extends Repository<Person, Long> {

//	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.codspire.db.mgmt");
//	private EntityManager em;

	public PersonRepository() {
		em = emf.createEntityManager();
	}

	@Override
	public Optional<Person> save(Person person) {
		Objects.requireNonNull(person, "Person must not be null");
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
		return Optional.of(person);
	}

	@Override
	public Optional<Person> findById(Long key) {
		em.getTransaction().begin();

		Person person = em.find(Person.class, key);
		em.getTransaction().commit();
		return Optional.ofNullable(person);
	}

	@Override
	public void delete(Person person) {
		em.getTransaction().begin();
		em.remove(person);
		em.getTransaction().commit();
	}
}
