package org.workshop.models;

import java.sql.Date;
import java.time.LocalDate;

public class Order {
    private int id;
    private Date created;
    private Date plan_start;
    private Date actual_start;
    private int employee_id;
    private String description_problem;
    private String description_repair;
    private int status_id;
    private int vehicle_id;
    private int price_customer;
    private int cost_parts;
    private int cost_h;
    private int h;
    private Date end;
    private int income;

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPlan_start() {
        return plan_start;
    }

    public void setPlan_start(Date plan_start) {
        this.plan_start = plan_start;
    }

    public Date getActual_start() {
        return actual_start;
    }

    public void setActual_start(Date actual_start) {
        this.actual_start = actual_start;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getDescription_problem() {
        return description_problem;
    }

    public void setDescription_problem(String description_problem) {
        this.description_problem = description_problem;
    }

    public String getDescription_repair() {
        return description_repair;
    }

    public void setDescription_repair(String description_repair) {
        this.description_repair = description_repair;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getPrice_customer() {
        return price_customer;
    }

    public void setPrice_customer(int price_customer) {
        this.price_customer = price_customer;
    }

    public int getCost_parts() {
        return cost_parts;
    }

    public void setCost_parts(int cost_parts) {
        this.cost_parts = cost_parts;
    }

    public int getCost_h() {
        return cost_h;
    }

    public void setCost_h(int cost_h) {
        this.cost_h = cost_h;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
