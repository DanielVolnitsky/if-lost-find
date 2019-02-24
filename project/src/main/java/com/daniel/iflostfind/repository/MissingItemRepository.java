package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.Loss;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingItemRepository extends CrudRepository<Loss, Long> {
}
