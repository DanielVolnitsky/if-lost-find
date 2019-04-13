package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LossRepository extends PagingAndSortingRepository<Loss, Long> {
    Page<Loss> findAllByLossGroup(LossGroup type, Pageable pageable);
}
