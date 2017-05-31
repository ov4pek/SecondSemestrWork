package ru.kpfu.itis.ovchinnikov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.ovchinnikov.model.Rent;
import ru.kpfu.itis.ovchinnikov.repository.RentRepository;
import ru.kpfu.itis.ovchinnikov.services.RentService;

import java.util.List;

/**
 * Created by danil on 30/05/17.
 */
@Service
@Transactional
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }


    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public void save(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public void remove(String id) {
        rentRepository.delete(Long.valueOf(id));
    }

    @Override
    public Rent findById(String id) {
        return rentRepository.findOne(Long.valueOf(id));
    }

    @Override
    public void update(Rent rent) {
        rentRepository.save(rent);
    }


}
