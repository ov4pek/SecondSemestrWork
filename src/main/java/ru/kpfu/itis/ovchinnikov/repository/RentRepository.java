package ru.kpfu.itis.ovchinnikov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.ovchinnikov.model.Rent;

public interface RentRepository extends JpaRepository<Rent,Long> {
}


