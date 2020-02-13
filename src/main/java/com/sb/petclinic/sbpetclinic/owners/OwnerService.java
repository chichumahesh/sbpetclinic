package com.sb.petclinic.sbpetclinic.owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    public Owners addOwner(Owners owners) {
        Owners owner =  ownerRepository.save(owners);
        return owner;
    }

   // @Transactional(readOnly = true)
    public OwnerList findOwnerByLastname(String lastName) {
        List<Owners> ownersList = ownerRepository.findByLastName(lastName);
        OwnerList ownerList = new OwnerList();
        ownerList.getOwnersList().addAll(ownersList);
        return ownerList;
    }

    public Owners findOwnerById(Integer id) {
       Optional<Owners> owner = ownerRepository.findById(id);
       return owner.get();
    }

    public OwnerList findAllOwners() {
        List<Owners> ownersList = ownerRepository.findAll();
        OwnerList ownerList = new OwnerList();
        ownerList.getOwnersList().addAll(ownersList);
        return ownerList;
    }


}
