package com.sb.petclinic.sbpetclinic.visits;

import com.sb.petclinic.sbpetclinic.owners.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VisitsRepositoryTest {

    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Test
    @Transactional
    public void saveVisits_shouldSaveVisits() {

        Owners owners = new Owners();
        owners.setFirstName("testFirstName");
        owners.setLastName("testLastName");
        owners.setCity("city1");
        owners.setTelephone("1234567890");
        owners.setAddress("address1");

        Owners dbOwners = ownerRepository.save(owners);
        Optional<Owners> owners1 = ownerRepository.findById(new Integer(dbOwners.getId()));

        Pet pet = new Pet();
        PetType type = new PetType();
        type.setId(2);
        type.setName("Dog");
        pet.setType(type);
        pet.setName("rinku");
        pet.setOwner(owners1.get());
        //pet.setBirthDate(LocalDate.now());

        Pet dbPet =  petRepository.save(pet);

       // Pet pet = new Pet();

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("desc1");
        visit.setPet(dbPet);

       Visit visit1 =  visitsRepository.save(visit);

       System.out.println(visit1);

    }
}