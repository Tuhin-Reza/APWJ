package com.service;

import com.domain.TaxRatePayCat;

import java.util.List;
public interface TaxRatePayCatService {

    public List<TaxRatePayCat> getAll();
    public TaxRatePayCat get(Long id);
    public TaxRatePayCat create(TaxRatePayCat taxRatePayCat);
    public TaxRatePayCat update(TaxRatePayCat taxRatePayCat);
    public void delete(Long id);
}
