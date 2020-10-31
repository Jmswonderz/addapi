package com.businessad.addapi.repository;

import com.businessad.addapi.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, String> {
    @Query("select distinct advert from Advert advert where advert.businessId in (select b.id from BusinessOwner b where b.locationId = ?1)")
    List<Advert> getAdvertByLocation(String location);
}
