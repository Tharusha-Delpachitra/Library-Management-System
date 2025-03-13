package com.project.backend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends User {

    @Override
    public String getRole(){
        return "MEMBER";
    }
}
