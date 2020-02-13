package com.sb.petclinic.sbpetclinic.owners;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest //load all the classes which is heavy
//@ContextConfiguration(classes={OwnerService.class,OwnerRepository.class})
    //@ActiveProfiles("test")
class OwnerServiceTest {

    @InjectMocks
    OwnerService ownerService;

    @Mock
    OwnerRepository ownerRepository;

    @Test
    public void addOwner_shouldReturnOwner() {
        Owners owners1 = new Owners();
        owners1.setFirstName("firstName");
        owners1.setLastName("lastName");
        when(ownerRepository.save(any(Owners.class))).thenReturn(owners1);

        Owners ownerReturn = ownerService.addOwner(owners1);

        assertEquals(owners1.getFirstName(), ownerReturn.getFirstName());
        assertEquals(owners1.getLastName(), ownerReturn.getLastName());
    }

    @Test
    public void findOwnerByLastname_returnsOwnerList() {
        List<Owners> ownersList = new ArrayList<>();
        ownersList.add(new Owners());
        ownersList.add(new Owners());
        when(ownerRepository.findByLastName(any(String.class))).thenReturn(ownersList);

        OwnerList ownerList = ownerService.findOwnerByLastname("lastName");

        assertNotNull(ownerList);
        assertEquals(ownerList.getOwnersList().size(), 2);
    }

    @Test
    public void findOwnerById_shouldReturnOwner() {
        Owners owners = new Owners();
        owners.setFirstName("firstName");
        when(ownerRepository.findById(1)).thenReturn(Optional.of(owners));

        Owners dbOwners = ownerService.findOwnerById(1);

        assertEquals(owners.getFirstName(), dbOwners.getFirstName());
    }

    @Test
    public void findAllOwners_shouldReturnOwnerList() {
        Owners owners = new Owners();
        owners.setFirstName("firstName");
        Owners owners1 = new Owners();
        owners1.setFirstName("firstName1");

        List<Owners> ownersList = new ArrayList<>();
        ownersList.add(owners);
        ownersList.add(owners1);

        when(ownerRepository.findAll()).thenReturn(ownersList);

        OwnerList ownerList = ownerService.findAllOwners();

        assertNotNull(ownerList);
        assertEquals(ownerList.getOwnersList().size(),2);
    }
}