package com.sb.petclinic.sbpetclinic.vets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableCaching
@Service
public class VetsService {

    @Autowired
    VetsRepository vetsRepository;

    @Cacheable("vets")
    public Vets getVets() {
        Vets vets = new Vets();
        List<Vet> vetList =  vetsRepository.findAll();
        vets.setVetList(vetList);
        return vets;
    }
}
