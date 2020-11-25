package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {

  @Autowired
  DistilleryRepository distilleryRepository;

  @Autowired
  WhiskyRepository whiskyRepository;

  @GetMapping(value="/distilleries")
  public ResponseEntity<List<Distillery>> getDistilleries(@RequestParam(name="region", required = false) String region, @RequestParam(name="has12", required = false) boolean has12){
    if (region != null){
      return new ResponseEntity<>(distilleryRepository.findDistilleriesByRegion(region), HttpStatus.OK);
    } else if (has12 == true) {
      return new ResponseEntity<>(distilleryRepository.findDistilleriesByWhiskiesAge((Integer) 12), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }
  }

  @GetMapping(value="/distilleries/{id}")
  public ResponseEntity getDistillery(@PathVariable Long id){
    return new ResponseEntity(distilleryRepository.findById(id), HttpStatus.OK);
  }

  @GetMapping(value="/distilleries/{id}/whiskies")
  public ResponseEntity getWhiskiesForDistillery(@PathVariable Long id, @RequestParam(name="age", required = false) Integer age){
    if (age != null){
      return new ResponseEntity(whiskyRepository.findWhiskiesByAgeAndDistilleryId(age, id), HttpStatus.OK);
    } else {
      return new ResponseEntity(whiskyRepository.findWhiskiesByDistilleryId(id), HttpStatus.OK);
    }
  }

  @PostMapping(value="/distilleries")
  public ResponseEntity<Distillery> postDistillery(@RequestBody Distillery distillery){
    distilleryRepository.save(distillery);
    return new ResponseEntity<>(distillery, HttpStatus.CREATED);
  }

}
