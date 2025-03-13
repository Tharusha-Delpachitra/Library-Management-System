package com.project.backend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LIBRARIAN")
public class Librarian extends User {

    @Override
    public String getRole(){
        return "LIBRARIAN";
    }

}
