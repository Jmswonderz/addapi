package com.businessad.addapi.repository;

import com.businessad.addapi.entities.Advert;
import com.businessad.addapi.entities.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {

}
