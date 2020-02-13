package com.sb.petclinic.sbpetclinic.owners;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/schema.sql","/data.sql"})
@ActiveProfiles("test")
public class OwnerControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void addOwner() {

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

        HttpEntity<Owners> httpEntity = new HttpEntity<>(owners);

        ResponseEntity<Owners> response = testRestTemplate.postForEntity("/owners/add", httpEntity, Owners.class);

        assertNotNull(response.getBody().getId());

    }

}
