package com.service;

import com.domain.TaxZone;
import com.repository.TaxZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class TaxZoneServiceImpl implements TaxZoneService{
    private TaxZoneRepository taxZoneRepository;
    public TaxZoneServiceImpl(TaxZoneRepository taxZoneRepository) {
        this.taxZoneRepository = taxZoneRepository;
    }

    @Transactional(readOnly = true)
    public List<TaxZone> getAll() {
        return taxZoneRepository.getAll();
    }

    @Transactional(readOnly = true)
    public TaxZone get(Long id) {
        return taxZoneRepository.get(id);
    }

    @Transactional
    public TaxZone create(TaxZone taxZone) {
        return taxZoneRepository.create(taxZone);
    }

    @Transactional
    public TaxZone update(TaxZone taxZone) {
        return taxZoneRepository.update(taxZone);
    }

    @Transactional
    public void delete(Long id) {taxZoneRepository.delete(id);
    }

}
