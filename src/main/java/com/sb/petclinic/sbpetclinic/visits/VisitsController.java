package com.sb.petclinic.sbpetclinic.visits;

import com.sb.petclinic.sbpetclinic.owners.Pet;
import com.sb.petclinic.sbpetclinic.owners.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VisitsController {

    @Autowired
    PetRepository petRepository;

    @Autowired
    VisitsRepository visitsRepository;

    @PostMapping("/owners/{ownerid}/pets/{petid}/visits")
    public ResponseEntity<Visit> addVisit(@PathVariable("petid") Integer petid, @RequestBody Visit visit) {
        Optional<Pet> pet = petRepository.findById(petid);
        if(pet.isPresent()){
            visit.setPet(pet.get());
            visit = visitsRepository.save(visit);
        }
        return new ResponseEntity(visit, HttpStatus.OK);
    }
}
