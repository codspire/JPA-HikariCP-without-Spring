package com.codspire.db.mgmt;


import com.codspire.db.mgmt.entity.Person;
import com.codspire.db.mgmt.repository.PersonRepository;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		Server server = null;
		PersonRepository repository = null;
		try {
			// Start H2 embedded database
			server = Server.createTcpServer().start();
			log.info("Server started");

			Person person = new Person();
			person.setFirstName("Elon");
			person.setLastName("Musk");
			person.setCreatedDate(LocalDateTime.now());
			person.setDateOfBirth(LocalDate.of(1971, Month.JUNE, 28));

			repository = new PersonRepository();
			// Create person
			repository.save(person);

			// Hibernate generates id of 1
			Optional<Person> p = repository.findById(1L);

			p.ifPresent(elon -> {
				log.info("Person from database: {}", elon);
				elon.setModifiedDate(LocalDateTime.now());
				elon.setMiddleName("Reeve");
			});
			// Update person record
			repository.save(p.get());

			p = Optional.empty();

			// Read updated record
			p = repository.findById(1L);
			p.ifPresent(consumer -> {
				log.info("Person updated: {}", consumer);
			});
			// Delete person
			repository.delete(p.get());

			p = Optional.empty();

			p = repository.findById(1L);

			log.info("Does person exist: {}", p.isPresent());

		} catch (SQLException e) {
			log.error("Error occurred in initialization: " + e.getMessage());
			e.printStackTrace();
		} finally {
			log.info("Closing Entity Manager Factory");
			if (repository != null)
				repository.close();
			log.info("Entity Manager Factory closed ");
			log.info("Server shutting down");
			if (server != null)
				server.stop();
			log.info("Shutdown complete");
		}
	}
}