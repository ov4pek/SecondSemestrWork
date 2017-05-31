package ru.kpfu.itis.ovchinnikov.model;


import javax.persistence.*;

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String client;
    private String number;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    @Column(name = "rent_date")
    private String rentDate;

    @Column(name = "return")
    private String returnDate;

    public Rent() {
    }

    public Rent(String client, String number, Car car, String rentDate, String returnDate) {

        this.client = client;
        this.number = number;
        this.car = car;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String nuber) {
        this.number = nuber;
    }

    public String getCar() {
        return car.getModel();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
