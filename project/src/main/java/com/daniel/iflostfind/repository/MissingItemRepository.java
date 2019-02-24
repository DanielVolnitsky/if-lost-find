package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.MissingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingItemRepository extends CrudRepository<MissingItem, Long> {
}
