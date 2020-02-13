package com.sb.petclinic.sbpetclinic.vets;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class VetsController {

    @Autowired
    VetsService vetsService;

    @GetMapping("/vets")
    @ApiOperation(value = "Find Vets", response = Vets.class, notes = "Api for getting the Vets information")
    public Vets getVets() {
       Vets vets =  vetsService.getVets();
       return vets;
    }
}
