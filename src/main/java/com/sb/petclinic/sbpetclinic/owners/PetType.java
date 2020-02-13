package com.sb.petclinic.sbpetclinic.owners;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "types", schema = "petstore_rest")
public class PetType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

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

//    @Override
//    public String toString() {
//        return "PetType{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
