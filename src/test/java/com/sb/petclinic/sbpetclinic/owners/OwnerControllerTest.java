package com.sb.petclinic.sbpetclinic.owners;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OwnerController.class)
class OwnerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    OwnerService ownerService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addOwner_inputsValid_returnSuccess() throws Exception{

        Owners owners1 = new Owners();
        owners1.setFirstName("firstName");
        owners1.setLastName("lastName");

        when(ownerService.addOwner(any(Owners.class))).thenReturn(owners1);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/add")
                    .content(objectMapper.writeValueAsString(owners1))
                    .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("firstName")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("lastName")));

        verify(ownerService).addOwner(any(Owners.class));
    }

    @Test
    public void getOwnerByLastName_shouldReturnOwnerInfo() throws Exception{
        Owners owners1 = new Owners();
        owners1.setFirstName("firstName");
        owners1.setLastName("lastName");
        List<Owners> list1 = new ArrayList<>();
        list1.add(owners1);

        OwnerList ownerList = new OwnerList();
        ownerList.setOwnersList(list1);

        String returnValue = objectMapper.writeValueAsString(ownerList);

        when(ownerService.findOwnerByLastname(anyString())).thenReturn(ownerList);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1astName")
               // .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownersList",Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownersList[0].firstName", Matchers.is("firstName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownersList[0].lastName", Matchers.is("lastName") ))
        ;

        verify(ownerService).findOwnerByLastname(anyString());


    }


    @Test
    public void updateOwner_shouldUpdateOwnerInfo() throws Exception{

        Owners owners1 = new Owners();
        owners1.setFirstName("firstName");
        owners1.setLastName("lastName");

        Owners inputOwner = new Owners();
        inputOwner.setFirstName("testFirstName");
        inputOwner.setLastName("testLastName");

        when(ownerService.findOwnerById(10)).thenReturn(owners1);
        when(ownerService.addOwner(any(Owners.class))).thenReturn(inputOwner);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/owners/10")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(owners1))

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("testFirstName")))
        .andReturn()
        ;



        verify(ownerService).findOwnerById(10);
        verify(ownerService).addOwner(any(Owners.class));


    }

//    @Test
//    public void addOwner_validInputs_returnSavedObject() {
//        Owners owners1 = new Owners();
//        owners1.setFirstName("firstName");
//        owners1.setLastName("lastName");
//
//        mockMvc.perform(MockMvcRequestBuilders.post())
//    }

}