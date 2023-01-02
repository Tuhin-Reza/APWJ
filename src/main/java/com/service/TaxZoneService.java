package com.service;

import com.domain.TaxZone;

import java.util.List;
public interface TaxZoneService {
    public List<TaxZone> getAll();
    public TaxZone get(Long id);
    public TaxZone create(TaxZone taxZone);
    public TaxZone update(TaxZone taxZone);
    public void delete(Long id);
}
