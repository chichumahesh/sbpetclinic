package com.sb.petclinic.sbpetclinic.owners;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.security.acl.Owner;
import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owners, Integer> {

//    @Query("select owners from Owners owners where owners.lastName like :lastName%")
//    List<Owners> findByLastName(@Param("lastName") String lastName);

    @Query("select owners from Owners owners left join fetch owners.pets where owners.lastName like :lastName%")
    List<Owners> findByLastName(@Param("lastName") String lastName);

}
