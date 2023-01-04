package com.repository;

import com.domain.ExemptedPercentage;
import com.domain.TaxHistory;

import java.util.List;

public interface TaxHistoryRepository {
    public List<TaxHistory> getAll();
    public TaxHistory create(TaxHistory taxHistory);
    public TaxHistory get(Long id);
    public void delete(Long id);
}
