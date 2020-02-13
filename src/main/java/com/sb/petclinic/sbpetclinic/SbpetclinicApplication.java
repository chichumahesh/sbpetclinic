package com.sb.petclinic.sbpetclinic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.petclinic.sbpetclinic.owners.Owners;
import com.sb.petclinic.sbpetclinic.owners.Pet;
import com.sb.petclinic.sbpetclinic.owners.PetType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SbpetclinicApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SbpetclinicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Owners owners = new Owners();
        owners.setLastName("testlast");
        owners.setFirstName("testfirst");
        owners.setAddress("test address");
        owners.setCity("test city");
        owners.setTelephone("1234567890");

        Pet pet1 = new Pet();
        pet1.setName("Dog");
       // pet1.setBirthDate(LocalDate.now());
        PetType type = new PetType();
        type.setId(2);
        type.setName("Dog");
        pet1.setOwner(owners);
        pet1.setType(type);

        Set<Pet> petSet = new HashSet<>();
        petSet.add(pet1);
        owners.setPets(petSet);

        ObjectMapper mapper = new ObjectMapper();
        //String str = mapper.writeValueAsString(pet1);
        String str = mapper.writeValueAsString(owners);
        System.out.println("......."+str);

    }
}


