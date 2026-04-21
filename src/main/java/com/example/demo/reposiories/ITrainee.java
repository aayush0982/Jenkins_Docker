package com.example.demo.reposiories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Trainee;

@Repository
public interface ITrainee extends JpaRepository<Trainee, Integer> {

	List<Trainee> findByName(String name);
	Trainee findByNameAndSalary(String name, double salary);
	Trainee findByNameOrSalary(String name, Double salary);

	List<Trainee> findBySalaryGreaterThan(double salary);
	List<Trainee> findBySalaryLessThan(double salary);

	List<Trainee> findBySalaryGreaterThanEqual(double salary);
	List<Trainee> findBySalaryLessThanEqual(double salary);

	Trainee findByNameIgnoreCase(String name);
}