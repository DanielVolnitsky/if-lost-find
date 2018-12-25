package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findUserByLogin(String login);
}
