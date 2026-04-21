package com.example.demo;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Trainee;
import com.example.demo.reposiories.ITrainee;

@Service
public class TraineeServices implements ITraineeSerivces {

    private static final Logger logger =
            LoggerFactory.getLogger(TraineeServices.class);

    @Autowired
    private ITrainee iTrainee;

    public List<Trainee> fetchAll() {
        logger.debug("Service: fetching all trainees from DB");
        return iTrainee.findAll();
    }

    public Optional<Trainee> fetchById(int id) {
        logger.debug("Service: fetching trainee by id: {}", id);
        return iTrainee.findById(id);
    }

    // rest unchanged (NO logging added)

    public List<Trainee> fetchByName(String name) {
        return iTrainee.findByName(name);
    }

    public void deleteById(int id) {
        iTrainee.deleteById(id);
    }

    public void updateTrainee(Trainee tr) {
        iTrainee.save(tr);
    }

    public void addTrainee(Trainee tr) {
        iTrainee.save(tr);
    }

    public Trainee findByNameAndSalary(String name, double salary) {
        return iTrainee.findByNameAndSalary(name, salary);
    }

    public Trainee findByNameOrSalary(String name, Double salary) {
        return iTrainee.findByNameOrSalary(name, salary);
    }

    public List<Trainee> findBySalaryGreaterThan(double salary) {
        return iTrainee.findBySalaryGreaterThan(salary);
    }

    public List<Trainee> findBySalaryLessThan(double salary) {
        return iTrainee.findBySalaryLessThan(salary);
    }

    public List<Trainee> findBySalaryGreaterThanEqual(double salary) {
        return iTrainee.findBySalaryGreaterThanEqual(salary);
    }

    public List<Trainee> findBySalaryLessThanEqual(double salary) {
        return iTrainee.findBySalaryLessThanEqual(salary);
    }

    public Trainee findByNameIgnoreCase(String name) {
        return iTrainee.findByNameIgnoreCase(name);
    }
}