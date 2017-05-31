package ru.kpfu.itis.ovchinnikov.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private int year;
    private int mileage;
    private int power;
    private int cost;

    public Car(String model, int year, int mileage, int power, int cost) {
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.power = power;
        this.cost = cost;
    }

    public Car() {
    }

    @OneToMany(mappedBy = "car")
    private Set<Rent> rents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<Rent> getRents() {
        return rents;
    }

    public void setRents(Set<Rent> rents) {
        this.rents = rents;
    }
}
