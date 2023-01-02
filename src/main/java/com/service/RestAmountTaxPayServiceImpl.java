package com.service;

import com.domain.RestAmountTaxPay;
import com.repository.RestAmountTaxPayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class RestAmountTaxPayServiceImpl implements RestAmountTaxPayService {

    private RestAmountTaxPayRepository restAmountTaxPayRepository;
    public RestAmountTaxPayServiceImpl(RestAmountTaxPayRepository restAmountTaxPayRepository) {

        this.restAmountTaxPayRepository = restAmountTaxPayRepository;
    }

    @Transactional(readOnly = true)
    public List<RestAmountTaxPay> getAll() {
        return restAmountTaxPayRepository.getAll();
    }

    @Transactional(readOnly = true)
    public RestAmountTaxPay  get(Long id) {
        return restAmountTaxPayRepository.get(id);
    }

    @Transactional
    public RestAmountTaxPay create(RestAmountTaxPay restAmountTaxPay) {
        return restAmountTaxPayRepository.create(restAmountTaxPay);
    }

    @Transactional
    public RestAmountTaxPay update(RestAmountTaxPay restAmountTaxPay) {
        return restAmountTaxPayRepository.update(restAmountTaxPay);
    }

    @Transactional
    public void delete(Long id) {
        restAmountTaxPayRepository.delete(id);
    }

}
