package com.sb.petclinic.sbpetclinic.visits;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.petclinic.sbpetclinic.owners.Pet;
import com.sb.petclinic.sbpetclinic.owners.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VisitsController.class)
class VisitsControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    PetRepository petRepository;
    @MockBean
    VisitsRepository visitsRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void addVisit_shouldAddVisits() throws Exception{


        ArgumentCaptor<Visit> captor = ArgumentCaptor.forClass(Visit.class);

        Visit visit = new Visit();
        visit.setDescription("desc1");

        Pet p1 = new Pet();
        p1.setName("petName");
        p1.setId(2);

        when(petRepository.findById(2)).thenReturn(Optional.of(p1));
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/2/visits")
        .contentType("application/json").content(objectMapper.writeValueAsString(visit))
        ).andReturn();

       assertEquals(result.getResponse().getStatus(),200);

       verify(petRepository).findById(2);
       verify(visitsRepository).save(captor.capture());

       Visit v2 = captor.getValue();

       assertNotNull(v2.getPet());

    }

}