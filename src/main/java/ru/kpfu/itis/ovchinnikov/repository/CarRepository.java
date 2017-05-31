package ru.kpfu.itis.ovchinnikov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.ovchinnikov.model.Car;

public interface CarRepository extends JpaRepository<Car,Long> {

    Car findByModel(String model);


}
