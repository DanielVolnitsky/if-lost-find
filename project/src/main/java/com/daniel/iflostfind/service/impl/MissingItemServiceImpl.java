package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.MissingItem;
import com.daniel.iflostfind.repository.MissingItemRepository;
import com.daniel.iflostfind.service.MissingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissingItemServiceImpl implements MissingItemService {

    private MissingItemRepository missingItemRepository;

    @Autowired
    public MissingItemServiceImpl(MissingItemRepository missingItemRepository) {
        this.missingItemRepository = missingItemRepository;
    }

    @Override
    public List<MissingItem> getAll() {
        return (List<MissingItem>) missingItemRepository.findAll();
    }
}
