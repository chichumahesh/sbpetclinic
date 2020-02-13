package com.sb.petclinic.sbpetclinic.vets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VetsServiceTest {

    @Autowired
    VetsService vetsService;

    @Test
    public void getVets() {
       Vets vets = vetsService.getVets();
       assertNotNull(vets);
       assertEquals(vets.getVetList().size(),6);
    }

}