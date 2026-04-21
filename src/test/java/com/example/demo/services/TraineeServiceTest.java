package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.TraineeServices;
import com.example.demo.entities.Trainee;
import com.example.demo.reposiories.ITrainee;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {

	@Mock
	private ITrainee traineeRepo;

	@InjectMocks
	private TraineeServices traineeService;

	@Test
	void testFetchAll() {
		List<Trainee> list = new ArrayList<>();
		list.add(new Trainee());
		list.add(new Trainee());

		Mockito.when(traineeRepo.findAll()).thenReturn(list);

		List<Trainee> result = traineeService.fetchAll();

		assertEquals(2, result.size());
		Mockito.verify(traineeRepo).findAll();
	}

	@Test
	void testFetchByName() {
		List<Trainee> list = new ArrayList<>();
		list.add(new Trainee());

		Mockito.when(traineeRepo.findByName("Aman")).thenReturn(list);

		List<Trainee> result = traineeService.fetchByName("Aman");

		assertNotNull(result);
		assertEquals(1, result.size());
		Mockito.verify(traineeRepo).findByName("Aman");
	}

	@Test
	void testFetchByIdFound() {
		Trainee t = new Trainee();

		Mockito.when(traineeRepo.findById(1)).thenReturn(Optional.of(t));

		Optional<Trainee> result = traineeService.fetchById(1);

		assertTrue(result.isPresent());
		Mockito.verify(traineeRepo).findById(1);
	}

	@Test
	void testFetchByIdNotFound() {
		Mockito.when(traineeRepo.findById(2)).thenReturn(Optional.empty());

		Optional<Trainee> result = traineeService.fetchById(2);

		assertFalse(result.isPresent());
		Mockito.verify(traineeRepo).findById(2);
	}

	@Test
	void testAddTrainee() {
		Trainee t = new Trainee();
		t.setName("Aman");

		Mockito.when(traineeRepo.save(t)).thenReturn(t);

		traineeService.addTrainee(t);

		Mockito.verify(traineeRepo).save(t);
	}

	@Test
	void testUpdateTrainee() {
		Trainee t = new Trainee();
		t.setName("Updated");

		Mockito.when(traineeRepo.save(t)).thenReturn(t);

		traineeService.updateTrainee(t);

		Mockito.verify(traineeRepo).save(t);
	}

	@Test
	void testDeleteById() {
		Mockito.doNothing().when(traineeRepo).deleteById(1);

		traineeService.deleteById(1);

		Mockito.verify(traineeRepo).deleteById(1);
	}

	@Test
	void testFindByNameAndSalary() {
		Trainee t = new Trainee();

		Mockito.when(traineeRepo.findByNameAndSalary("Aman", 50000)).thenReturn(t);

		Trainee result = traineeService.findByNameAndSalary("Aman", 50000);

		assertNotNull(result);
		Mockito.verify(traineeRepo).findByNameAndSalary("Aman", 50000);
	}

	@Test
	void testFindByNameOrSalary() {
		Trainee t = new Trainee();

		Mockito.when(traineeRepo.findByNameOrSalary("Aman", 50000.0)).thenReturn(t);

		Trainee result = traineeService.findByNameOrSalary("Aman", 50000.0);

		assertNotNull(result);
		Mockito.verify(traineeRepo).findByNameOrSalary("Aman", 50000.0);
	}

	@Test
	void testFindBySalaryGreaterThan() {
		List<Trainee> list = List.of(new Trainee());

		Mockito.when(traineeRepo.findBySalaryGreaterThan(30000)).thenReturn(list);

		List<Trainee> result = traineeService.findBySalaryGreaterThan(30000);

		assertEquals(1, result.size());
		Mockito.verify(traineeRepo).findBySalaryGreaterThan(30000);
	}

	@Test
	void testFindBySalaryLessThan() {
		List<Trainee> list = List.of(new Trainee());

		Mockito.when(traineeRepo.findBySalaryLessThan(30000)).thenReturn(list);

		List<Trainee> result = traineeService.findBySalaryLessThan(30000);

		assertEquals(1, result.size());
		Mockito.verify(traineeRepo).findBySalaryLessThan(30000);
	}

	@Test
	void testFindBySalaryGreaterThanEqual() {
		List<Trainee> list = List.of(new Trainee());

		Mockito.when(traineeRepo.findBySalaryGreaterThanEqual(30000)).thenReturn(list);

		List<Trainee> result = traineeService.findBySalaryGreaterThanEqual(30000);

		assertEquals(1, result.size());
		Mockito.verify(traineeRepo).findBySalaryGreaterThanEqual(30000);
	}

	@Test
	void testFindBySalaryLessThanEqual() {
		List<Trainee> list = List.of(new Trainee());

		Mockito.when(traineeRepo.findBySalaryLessThanEqual(30000)).thenReturn(list);

		List<Trainee> result = traineeService.findBySalaryLessThanEqual(30000);

		assertEquals(1, result.size());
		Mockito.verify(traineeRepo).findBySalaryLessThanEqual(30000);
	}

	@Test
	void testFindByNameIgnoreCase() {
		Trainee t = new Trainee();

		Mockito.when(traineeRepo.findByNameIgnoreCase("aman")).thenReturn(t);

		Trainee result = traineeService.findByNameIgnoreCase("aman");

		assertNotNull(result);
		Mockito.verify(traineeRepo).findByNameIgnoreCase("aman");
	}
}