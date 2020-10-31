package com.businessad.addapi.controllers;

import com.businessad.addapi.IBaseController;
import com.businessad.addapi.entities.BusinessOwner;
import com.businessad.addapi.entities.Location;
import com.businessad.addapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LocationController implements IBaseController<Location> {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/locations")
    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @GetMapping("/locations/{id}")
    @Override
    public ResponseEntity<Location> get(String id) {
        var location = locationRepository.getOne(id);
        return ResponseEntity.ok().body(location);
    }

    @PostMapping("locations")
    @Override
    public Location create(@Validated @RequestBody Location entity) {
        return locationRepository.save(entity);
    }

    @DeleteMapping("locations/{id}")
    @Override
    public ResponseEntity<String> delete(String id) {
        locationRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PutMapping("/locations")
    @Override
    public ResponseEntity<Location> Update(@PathVariable String id, @Validated @RequestBody Location entity) {
        var location = locationRepository.getOne(id);
        location.setName(entity.getName());

        try {
            locationRepository.save(location);
            return ResponseEntity.ok().body(entity);
        }catch (Exception ex){
            return ResponseEntity.ok().body(null);
        }
    }

}
