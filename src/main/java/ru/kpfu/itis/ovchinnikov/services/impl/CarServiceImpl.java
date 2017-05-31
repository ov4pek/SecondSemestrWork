package ru.kpfu.itis.ovchinnikov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.ovchinnikov.model.Car;
import ru.kpfu.itis.ovchinnikov.repository.CarRepository;
import ru.kpfu.itis.ovchinnikov.services.CarService;

import java.util.List;

/**
 * Created by danil on 30/05/17.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {



    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public Car getCar(String text) {
        return carRepository.findByModel(text);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void remove(Long id) {
        carRepository.delete(id);
    }

    @Override
    public Car getCarById(String id) {
        return carRepository.findOne(Long.valueOf(id));
    }
}

