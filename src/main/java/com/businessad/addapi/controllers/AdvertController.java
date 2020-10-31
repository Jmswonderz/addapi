package com.businessad.addapi.controllers;

import com.businessad.addapi.entities.Advert;
import com.businessad.addapi.entities.Location;
import com.businessad.addapi.entities.User;
import com.businessad.addapi.repository.AdvertRepository;
import com.businessad.addapi.repository.BusinessOwnerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.persistence.TypedQuery;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdvertController {

    @Autowired
    private AdvertRepository advertRepository;

    @GetMapping("/adverts")
    public List<Advert> getAll(){
        return advertRepository.findAll();
    }


    @GetMapping("/adverts/{location}/location")
    public List<Advert> getAllByLocation(@PathVariable(value = "location") String location){

        var adverts = advertRepository.getAdvertByLocation(location);

        return adverts;
    }


    @GetMapping("/adverts/{id}")
    public ResponseEntity<Advert> getAdvert(@PathVariable(value = "id") String id) {
        Advert advert = advertRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("User not found"));

        return ResponseEntity.ok().body(advert);
    }

    @PostMapping("/adverts")
    public Advert createdAdvert(@Validated @RequestBody Advert advertDetails){
        return advertRepository.save(advertDetails);
    }

    @DeleteMapping("/adverts")
    public ResponseEntity<String> deleteAdvert(String id){
        advertRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PutMapping("/adverts")
    public ResponseEntity<Advert> Update(@PathVariable String id, @Validated @RequestBody Advert entity) {
        var advert = advertRepository.getOne(id);
        advert.setBody(entity.getBody());
        advert.setBusinessId(entity.getBusinessId());
        advert.setTitle(entity.getTitle());

        try {
            advertRepository.save(advert);
            return ResponseEntity.ok().body(entity);
        }catch (Exception ex){
            return ResponseEntity.ok().body(null);
        }
    }
}
