package com.zinalabs.subscriptionreminder.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Beraki on 6/11/2017.
 */

public class Customer implements Serializable {


    int id, status, telephone;
    String name, address;

    ArrayList<Customer> customers=null;


    public Customer(){

    }

    public Customer(int id, String name, String address, int telephone, int status){
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.telephone = telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getTelephone() {
        return telephone;
    }
}
