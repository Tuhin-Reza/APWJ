package com.repository;

import com.domain.TaxRatePayCat;

import java.util.List;

public interface TaxRatePayCatRepository {

    public List<TaxRatePayCat> getAll();
    public TaxRatePayCat get(Long id);
    public TaxRatePayCat create(TaxRatePayCat taxRatePayCat);
    public TaxRatePayCat update(TaxRatePayCat taxRatePayCat);
    public void delete(Long id);
}
