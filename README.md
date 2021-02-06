Sometime you don't need all the goodies of Spring and have to go alone. This example implements JPA using high performance Hikari connection pool without Spring.

Inspired by
- https://github.com/juliuskrah/java-crud/tree/hikari-hibernate-jpa
- https://stackoverflow.com/questions/1989672/create-jpa-entitymanager-without-persistence-xml-configuration-file

This demo programmatically builds JPA persistence configuration, so that `persistence.xml` is not needed.

If you want to use `persistence.xml`, just rename `src/main/resources/META-INF/persistence.xml.not_used` 

and replace `EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(archiverPersistenceUnitInfo(), config());`

with `EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.codspire.db.mgmt");`