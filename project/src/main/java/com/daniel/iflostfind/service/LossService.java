package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.Loss;

import java.util.List;

public interface LossService {
    void add(Loss loss);
    List<Loss> getAll();
}
