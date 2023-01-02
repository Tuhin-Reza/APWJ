package com.repository;

import com.domain.ExemptedPercentage;

import java.util.List;

public interface ExemptedPercentageRepository {

    public List<ExemptedPercentage> getAll();
    public ExemptedPercentage get(Long id);
    public ExemptedPercentage create(ExemptedPercentage exemptedPercentage);
    public ExemptedPercentage update(ExemptedPercentage exemptedPercentage);
    public void delete(Long id);

}
