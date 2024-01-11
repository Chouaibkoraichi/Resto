package com.restorant.backend.PojoInput;

import com.restorant.backend.POJO.Address;

public class UserInput {

    private String firstName;
    private String lastName;
    private String address;
    private Integer role;
    private String phoneNo;

    public UserInput(String firstName, String lastName, String address, String phoneNo, Integer role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
