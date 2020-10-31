package com.businessad.addapi.repository;

import com.businessad.addapi.entities.BusinessOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessOwnerRepository  extends JpaRepository<BusinessOwner, String> {
    @Query("select distinct b.locationId from BusinessOwner b")
    public List<Object> getLocations();
}
