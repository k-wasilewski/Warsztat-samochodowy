package org.workshop.models;

import java.sql.Date;
import java.time.LocalDate;

public class Vehicle {
    private int id;
    private String model;
    private String make;
    private Date dop;
    private String lic;
    private Date next_service;
    private int customer_id;

    public Vehicle() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Date getDop() {
        return dop;
    }

    public void setDop(Date dop) {
        this.dop = dop;
    }

    public String getLic() {
        return lic;
    }

    public void setLic(String lic) {
        this.lic = lic;
    }

    public Date getNext_service() {
        return next_service;
    }

    public void setNext_service(Date next_service) {
        this.next_service = next_service;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
