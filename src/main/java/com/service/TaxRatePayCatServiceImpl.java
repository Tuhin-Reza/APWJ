package com.service;

import com.domain.TaxRatePayCat;
import com.repository.TaxRatePayCatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class TaxRatePayCatServiceImpl implements TaxRatePayCatService {

    private TaxRatePayCatRepository taxRatePayCatRepository;
    public TaxRatePayCatServiceImpl(TaxRatePayCatRepository taxRatePayCatRepository) {

        this.taxRatePayCatRepository = taxRatePayCatRepository;
    }

    @Transactional(readOnly = true)
    public List<TaxRatePayCat> getAll() {
        return taxRatePayCatRepository.getAll();
    }

    @Transactional(readOnly = true)
    public TaxRatePayCat get(Long id) {
        return taxRatePayCatRepository.get(id);
    }

    @Transactional
    public TaxRatePayCat create(TaxRatePayCat taxRatePayCat) {
        return taxRatePayCatRepository.create(taxRatePayCat);
    }

    @Transactional
    public TaxRatePayCat update(TaxRatePayCat taxRatePayCat) {
        return taxRatePayCatRepository.update(taxRatePayCat);
    }

    @Transactional
    public void delete(Long id) {
        taxRatePayCatRepository.delete(id);
    }

}
