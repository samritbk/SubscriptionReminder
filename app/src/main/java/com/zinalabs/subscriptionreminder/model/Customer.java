package com.zinalabs.subscriptionreminder.model;

import java.util.ArrayList;

/**
 * Created by Beraki on 6/11/2017.
 */

public class Customer {


    int id, status;
    String name, address;

    ArrayList<Customer> customers=null;


    public Customer(){

    }

    public Customer(int id, String name, String address, int status){
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
    }

    public void add(int id, String name, String address, int status){
        Customer customer=new Customer(id, name, address, status);
        this.customers.add(customer);
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
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
}
