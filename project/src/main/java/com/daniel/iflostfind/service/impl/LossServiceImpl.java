package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.service.LossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LossServiceImpl implements LossService {

    private final LossRepository lossRepository;

    @Autowired
    public LossServiceImpl(LossRepository lossRepository) {
        this.lossRepository = lossRepository;
    }

    @Override
    public void add(Loss loss) {
        lossRepository.save(loss);
    }
}
