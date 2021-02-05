# spring-boot-liquibase
Manage database schema using an independent Spring boot Liquibase App

Apply DB Migration

```mvn clean package```

```java -jar -Dspring.profiles.active=dev  target/spring-boot-liquibase-0.0.1-SNAPSHOT.jar```

or

```mvn spring-boot:run  -Dspring.profiles.active=dev```