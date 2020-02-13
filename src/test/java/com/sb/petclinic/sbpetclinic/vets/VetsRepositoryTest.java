package com.sb.petclinic.sbpetclinic.vets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VetsRepositoryTest {

    @Autowired
    VetsRepository  vetsRepository;

    @Test
    public void getAllVets() {

       List<Vet> vetList =  vetsRepository.findAll();

       assertNotNull(vetList);
       assertEquals(vetList.size(), 6);
    }

    @Test
    public void saveVets() {

        Specialty sp1 = new Specialty();
        sp1.setName("speciality1");

        Vet v1 = new Vet();
        v1.setFirstName("vetFirstName");
        v1.setLastName("vetLastName");
        v1.addSpeciality(sp1);

        Vet v2 = vetsRepository.saveAndFlush(v1);
        assertNotNull(v2);
    }
}