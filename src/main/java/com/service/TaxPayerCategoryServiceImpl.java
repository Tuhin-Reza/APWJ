package com.service;

import com.domain.TaxPayerCategory;
import com.repository.TaxPayerCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class TaxPayerCategoryServiceImpl implements TaxPayerCategoryService {

    private TaxPayerCategoryRepository taxPayerCategoryRepository;
    public TaxPayerCategoryServiceImpl(TaxPayerCategoryRepository taxPayerCategoryRepository) {
;
        this.taxPayerCategoryRepository=taxPayerCategoryRepository;
    }

    @Transactional(readOnly = true)
    public List<TaxPayerCategory> getAll() {
        return taxPayerCategoryRepository.getAll();
    }

    @Transactional(readOnly = true)
    public TaxPayerCategory get(Long id) {
        return taxPayerCategoryRepository.get(id);
    }

    @Transactional
    public TaxPayerCategory create(TaxPayerCategory taxPayerCategory) {
        return taxPayerCategoryRepository.create(taxPayerCategory);
    }

    @Transactional
    public TaxPayerCategory update(TaxPayerCategory taxPayerCategory) {
        return taxPayerCategoryRepository.update(taxPayerCategory);
    }

    @Transactional
    public void delete(Long id) {
        taxPayerCategoryRepository.delete(id);
    }

}
