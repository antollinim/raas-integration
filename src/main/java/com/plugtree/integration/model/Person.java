package com.plugtree.integration.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int numberOfChildren;
    private Date birthDate;
    
    public Person() {
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String toString(){
        return getName() + ": Birthdate: " + getBirthDate() + ", children: " + getNumberOfChildren();   
    }
    
}
