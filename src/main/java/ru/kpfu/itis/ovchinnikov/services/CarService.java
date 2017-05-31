package ru.kpfu.itis.ovchinnikov.services;

import ru.kpfu.itis.ovchinnikov.model.Car;

import java.util.List;

public interface CarService {


    Car getCar(String text);

    List<Car> findAll();

    void save(Car car);

    void remove(Long id);

    Car getCarById(String id);
}