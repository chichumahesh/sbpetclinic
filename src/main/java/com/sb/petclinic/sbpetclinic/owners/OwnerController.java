package com.sb.petclinic.sbpetclinic.owners;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
//@RequestMapping("/api")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    // adding new owner
    @PostMapping("/owners/add")
    @ApiOperation(value = "Add owner",response = Owners.class,notes = "Method to return owners",
            httpMethod = "POST",produces = "application/json", consumes = "application/json")
    public ResponseEntity<Owners> addOwner(
            @ApiParam(value = "owner input that needs to be passed",required = true)
            @RequestBody Owners owners) {
        Owners owner = ownerService.addOwner(owners);
        return ResponseEntity.ok(owner);
    }

    // find a owner by last name
    @GetMapping("/owners/{lastName}")
    @ApiOperation(value = "Get owner info",notes = "getting the owner information",response = OwnerList.class)
    public ResponseEntity<OwnerList> getOwnerInfo(
            @ApiParam(value = "the parameter to be passed", required = true)
            @PathVariable("lastName") String lastName) {
        OwnerList ownersList = ownerService.findOwnerByLastname(lastName);
        ResponseEntity responseEntity = new ResponseEntity(ownersList, HttpStatus.OK);
        return responseEntity;
    }

    //update owner
    @PutMapping("/owners/{id}")
    public ResponseEntity<Owners> updateOwner(@RequestBody Owners owners, @PathVariable("id") Integer id) {
        Owners dbOwner = ownerService.findOwnerById(id);
        dbOwner.setAddress(owners.getAddress());
        dbOwner.setTelephone(owners.getTelephone());
        dbOwner.setCity(owners.getCity());
        dbOwner.setFirstName(owners.getFirstName());
        dbOwner.setLastName(owners.getLastName());

        Owners dbOwner2 =ownerService.addOwner(dbOwner);

        return ResponseEntity.ok(dbOwner2);
    }

    @GetMapping("owners/all")
    public ResponseEntity<OwnerList> getOwners() {
        OwnerList ownersList = ownerService.findAllOwners();
        ResponseEntity responseEntity = new ResponseEntity(ownersList, HttpStatus.OK);
        return responseEntity;
    }

}
