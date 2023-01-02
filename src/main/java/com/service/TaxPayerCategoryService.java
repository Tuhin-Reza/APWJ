package com.service;

import com.domain.TaxPayerCategory;

import java.util.List;
public interface TaxPayerCategoryService {

    public List<TaxPayerCategory> getAll();
    public TaxPayerCategory get(Long id);
    public TaxPayerCategory create(TaxPayerCategory taxPayerCategory);
    public TaxPayerCategory update(TaxPayerCategory taxPayerCategory);
    public void delete(Long id);
}
