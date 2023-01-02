package com.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class TaxRatePayCatServiceImpl implements {

    private Repository Repository;
    public ServiceImpl(Repository Repository) {

        this.Repository = Repository;
    }

    @Transactional(readOnly = true)
    public List<> getAll() {
        return Repository.getAll();
    }

    @Transactional(readOnly = true)
    public  get(Long id) {
        return Repository.get(id);
    }

    @Transactional
    public create() {
        return Repository.create();
    }

    @Transactional
    public update() {
        return Repository.update();
    }

    @Transactional
    public void delete(Long id) {
        Repository.delete(id);
    }

}
