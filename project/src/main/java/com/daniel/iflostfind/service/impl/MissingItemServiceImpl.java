package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.service.MissingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissingItemServiceImpl implements MissingItemService {

    private LossRepository missingItemRepository;

    @Autowired
    public MissingItemServiceImpl(LossRepository missingItemRepository) {
        this.missingItemRepository = missingItemRepository;
    }

    @Override
    public List<Loss> getAll() {
        return (List<Loss>) missingItemRepository.findAll();
    }
}
