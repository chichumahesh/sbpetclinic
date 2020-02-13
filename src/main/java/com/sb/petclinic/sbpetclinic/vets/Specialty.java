package com.sb.petclinic.sbpetclinic.vets;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "specialties", schema = "petstore_rest")
public class Specialty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "specialties")
   // @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "specialty_id"), inverseJoinColumns = @JoinColumn(name = "vet_id"))
    private Set<Vet> vetSet = new HashSet<>();

    public Set<Vet> getVetSet() {
        return vetSet;
    }

    public void setVetSet(Set<Vet> vetSet) {
        this.vetSet = vetSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
