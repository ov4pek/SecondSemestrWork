package ru.kpfu.itis.ovchinnikov.services;

import ru.kpfu.itis.ovchinnikov.model.Rent;

import java.util.List;

public interface RentService {

    List<Rent> findAll();

    void save(Rent rent);

    void remove(String id);

    Rent findById(String id);

    void update(Rent rent);
}