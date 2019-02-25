package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.Loss;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LossRepository extends CrudRepository<Loss, Long> {
}
