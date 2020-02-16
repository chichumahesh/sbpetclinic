package com.sb.petclinic.sbpetclinic.vets;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class VetsServiceTest {

    @Mock
    VetsService vetsService;

    @Test
    public void getVets() {
        List<Vet> testList = Arrays.asList(mock(Vet.class), mock(Vet.class),mock(Vet.class),mock(Vet.class),mock(Vet.class),mock(Vet.class));

        Vets vetsTest = new Vets();
        vetsTest.setVetList(testList);


        when(vetsService.getVets()).thenReturn(vetsTest);
       Vets vets = vetsService.getVets();
       assertNotNull(vets);
       assertEquals(vets.getVetList().size(),6);
    }

}