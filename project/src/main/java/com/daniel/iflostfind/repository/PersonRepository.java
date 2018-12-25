package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User finUserByLogin(String login);
}
