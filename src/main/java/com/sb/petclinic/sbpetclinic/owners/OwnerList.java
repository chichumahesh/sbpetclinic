package com.sb.petclinic.sbpetclinic.owners;

import java.util.ArrayList;
import java.util.List;

public class OwnerList {

    private List<Owners> ownersList = new ArrayList<>();

    public List<Owners> getOwnersList() {
        return ownersList;
    }

    public void setOwnersList(List<Owners> ownersList) {
        this.ownersList = ownersList;
    }
}
