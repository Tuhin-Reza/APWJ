package com.service;

import com.domain.ExemptedPercentage;
import com.repository.ExemptedPercentageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ExemptedPercentageServiceImpl implements ExemptedPercentageService {

    private ExemptedPercentageRepository exemptedPercentageRepository;
    public ExemptedPercentageServiceImpl(ExemptedPercentageRepository exemptedPercentageRepository) {

        this.exemptedPercentageRepository = exemptedPercentageRepository;
    }

    @Transactional(readOnly = true)
    public List<ExemptedPercentage> getAll() {
        return exemptedPercentageRepository.getAll();
    }

    @Transactional(readOnly = true)
    public ExemptedPercentage get(Long id) {
        return exemptedPercentageRepository.get(id);
    }

    @Transactional
    public ExemptedPercentage create(ExemptedPercentage exemptedPercentage) {
        return exemptedPercentageRepository.create(exemptedPercentage);
    }

    @Transactional
    public ExemptedPercentage update(ExemptedPercentage exemptedPercentage) {
        return exemptedPercentageRepository.update(exemptedPercentage);
    }

    @Transactional
    public void delete(Long id) {
        exemptedPercentageRepository.delete(id);
    }

}
