package com.sb.petclinic.sbpetclinic.owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api")
public class PetController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private PetTypeRepository petTypeRepository;
    @Autowired
    PetRepository petRepository;

    // add new pets
    @PostMapping("/owners/{ownerid}/pets")
    public ResponseEntity<Pet> addPet(@PathVariable("ownerid") Integer ownerid, @RequestBody PetRequest petRequest) {

        //find the owner
        Pet pet = new Pet();
        Owners owner = ownerService.findOwnerById(ownerid);
        petTypeRepository.findById(petRequest.getTypeId()).ifPresent(pet::setType);
        pet.setName(petRequest.getName());
        pet.setOwner(owner);
        Pet dbPet = petRepository.save(pet);

        return new ResponseEntity(dbPet, HttpStatus.OK);

    }

    //update existing pets
    @PutMapping("/owners/{ownerid}/pets/{petid}")
    public Pet updatePets(@PathVariable("ownerid") Integer ownerid,
                           @PathVariable("petid") Integer petid, @RequestBody PetRequest petRequest) {

       Pet pet = petRepository.findById(petid).get();
       petTypeRepository.findById(petRequest.getTypeId()).ifPresent(pet::setType);
       pet.setName(petRequest.getName());

       Pet petSaved = petRepository.save(pet);
       return petSaved;
    }

}
