package net.loobo.medicare.db.repository;

import net.loobo.medicare.db.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findByFirstName(String firstName);
}