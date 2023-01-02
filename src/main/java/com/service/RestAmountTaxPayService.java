package com.service;

import com.domain.RestAmountTaxPay;

import java.util.List;
public interface RestAmountTaxPayService {

    public List<RestAmountTaxPay> getAll();
    public RestAmountTaxPay get(Long id);
    public RestAmountTaxPay create(RestAmountTaxPay restAmountTaxPay);
    public RestAmountTaxPay update(RestAmountTaxPay restAmountTaxPay);
    public void delete(Long id);
}
