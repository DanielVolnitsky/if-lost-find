package com.daniel.iflostfind.repository;

import com.daniel.iflostfind.domain.Finding;
import com.daniel.iflostfind.domain.FindingGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LossRepository extends PagingAndSortingRepository<Finding, Long> {
    Page<Finding> findAllByFindingGroupOrderByDateFound(FindingGroup type, Pageable pageable);
    Page<Finding> findAllByOrderByDateFound(Pageable pageable);
    List<Finding> findAllByOrderByDateFound();
}
