package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.Finding;
import com.daniel.iflostfind.domain.FindingGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FindingRepository extends PagingAndSortingRepository<Finding, Long> {
    Page<Finding> findAllByFindingGroupOrderByDateFoundDesc(FindingGroup type, Pageable pageable);

    Page<Finding> findAllByOrderByDateFoundDesc(Pageable pageable);

    List<Finding> findAllByOrderByDateFoundDesc();
}

