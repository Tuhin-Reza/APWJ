package com.repository;

import com.domain.TaxZone;

import java.util.List;
public interface TaxZoneRepository {

    public List<TaxZone> getAll();
    public TaxZone get(Long id);
    public TaxZone create(TaxZone taxZone);
    public TaxZone update(TaxZone taxZone);
    public void delete(Long id);
}
