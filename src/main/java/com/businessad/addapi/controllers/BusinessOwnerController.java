package com.businessad.addapi.controllers;

import com.businessad.addapi.IBaseController;
import com.businessad.addapi.entities.BusinessOwner;
import com.businessad.addapi.repository.BusinessOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BusinessOwnerController implements IBaseController<BusinessOwner> {

    @Autowired
    private BusinessOwnerRepository businessOwnerRepository;

    @GetMapping("/businessowners")
    @Override
    public List<BusinessOwner> getAll() {
        return businessOwnerRepository.findAll();
    }

    @GetMapping("/businessowners/{id}")
    @Override
    public ResponseEntity<BusinessOwner> get(String id) {
        var businessOwner = businessOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("User not found"));

        return ResponseEntity.ok().body(businessOwner);
    }

    @PostMapping("/businessowners")
    @Override
    public BusinessOwner create(@Validated @RequestBody BusinessOwner entity) {
        return businessOwnerRepository.save(entity);
    }

    @DeleteMapping("/businessowners")
    @Override
    public ResponseEntity<String> delete(String id) {
        businessOwnerRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PutMapping("/businessowners")
    @Override
    public ResponseEntity<BusinessOwner> Update(@PathVariable String id, @Validated @RequestBody BusinessOwner entity) {
        var businessOwner = businessOwnerRepository.getOne(id);
        businessOwner.setContact(entity.getContact());
        businessOwner.setlocationId(entity.getlocationId());
        businessOwner.setName(entity.getName());

        try {
            businessOwnerRepository.save(businessOwner);
            return ResponseEntity.ok().body(entity);
        }catch (Exception ex){
            return ResponseEntity.ok().body(null);
        }
    }
}
