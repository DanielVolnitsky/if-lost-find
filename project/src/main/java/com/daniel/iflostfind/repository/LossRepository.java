package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LossRepository extends PagingAndSortingRepository<Loss, Long> {

    List<Loss> findAllByType(LossType type, Pageable pageable);
}
