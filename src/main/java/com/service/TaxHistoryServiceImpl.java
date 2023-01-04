package com.service;

import com.domain.ExemptedPercentage;
import com.domain.TaxHistory;
import com.repository.TaxHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaxHistoryServiceImpl implements TaxHistoryService {
    private TaxHistoryRepository taxHistoryRepository;

    public TaxHistoryServiceImpl(TaxHistoryRepository taxHistoryRepository){
        this.taxHistoryRepository=taxHistoryRepository;
    }
    @Transactional(readOnly = true)
    public List<TaxHistory> getAll() {
        return taxHistoryRepository.getAll();
    }

    @Transactional
    public TaxHistory create(TaxHistory taxHistory) {
        return taxHistoryRepository.create(taxHistory);
    }

    @Transactional(readOnly = true)
    public TaxHistory get(Long id) {
        return taxHistoryRepository.get(id);
    }

    @Transactional
    public void delete(Long id) {
        taxHistoryRepository.delete(id);
    }
}
