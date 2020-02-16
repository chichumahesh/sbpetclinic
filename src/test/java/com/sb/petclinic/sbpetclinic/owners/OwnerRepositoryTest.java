package com.sb.petclinic.sbpetclinic.owners;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class OwnerRepositoryTest {

    @Autowired
    OwnerRepository ownerRepository;

    @Test
    @Transactional
    public void testAddOwner_shouldAddNewOwner() {

        Owners owners = new Owners();
        owners.setFirstName("testFirstName");
        owners.setLastName("testLastName");
        owners.setCity("city1");
        owners.setTelephone("1234567890");
        owners.setAddress("address1");
        Owners owners1 = ownerRepository.save(owners);
        assertNotNull(owners1.getFirstName());
    }


    @Test
    @Transactional
    public void addOwnerWithPets() {
        Owners owners = new Owners();
        owners.setFirstName("testFirstName2");
        owners.setLastName("testLastName2");
        owners.setCity("city2");
        owners.setTelephone("1234567890");
        owners.setAddress("address2");

        PetType type = new PetType();
        type.setId(2);
        type.setName("Dog");

        Pet pet = new Pet();
        pet.setName("petName1");
        pet.setOwner(owners);
        pet.setType(type);
        Set<Pet> petSet = new HashSet<>();
        petSet.add(pet);
        owners.setPets(petSet);

        Owners dbOwner = ownerRepository.save(owners);

        assertNotNull(dbOwner);
        assertEquals(dbOwner.getFirstName(), "testFirstName2");
        assertNotNull(dbOwner.getPets());
        assertEquals(dbOwner.getPets().size(), 1);
        //assertNotNull(dbOwner.getPets().iterator().next().getId());

    }

    @Test
    @Transactional
    public void testFetchOwnerInfo() throws JsonProcessingException {

        Owners owners = new Owners();
        owners.setFirstName("testFirstName");
        owners.setLastName("testLastName");
        owners.setCity("city1");
        owners.setTelephone("1234567890");
        owners.setAddress("address1");

        Owners owners1 = ownerRepository.save(owners);

        Optional<Owners> owners2 = ownerRepository.findById(owners1.getId());
        assertEquals(owners2.isPresent(), true);
        assertEquals(owners2.get().getFirstName(), "testFirstName");
    }
}