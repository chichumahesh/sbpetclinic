package com.sb.petclinic.sbpetclinic.owners;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Integer> {

}
