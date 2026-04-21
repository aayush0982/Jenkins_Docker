package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Trainee;

@RestController
public class TraineeController {

	@Autowired
	private TraineeServices trs;

	@GetMapping("/trainees")
	public List<Trainee> fetchAllTrainee() {
		return trs.fetchAll();
	}

	@GetMapping("/trainees/{id}")
	public Trainee fetchById(@PathVariable int id) {
		Optional<Trainee> t = trs.fetchById(id);
		return t.orElse(null);
	}

	@PostMapping("/trainees")
	public String addTrainee(@RequestBody Trainee t) {
		trs.addTrainee(t);
		return "Trainee Added Successfully";
	}

	@PutMapping("/trainees/{id}")
	public String updateTrainee(@PathVariable int id, @RequestBody Trainee t) {
		Optional<Trainee> existing = trs.fetchById(id);

		if (existing.isPresent()) {
			t.setId(id);
			trs.updateTrainee(t);
			return "Trainee Updated Successfully";
		} else {
			return "Trainee Not Found";
		}
	}

	@DeleteMapping("/trainees/{id}")
	public String deleteTrainee(@PathVariable int id) {
		Optional<Trainee> existing = trs.fetchById(id);

		if (existing.isPresent()) {
			trs.deleteById(id);
			return "Trainee Deleted Successfully";
		} else {
			return "Trainee Not Found";
		}
	}

	@GetMapping("/trainees/byId")
	public ResponseEntity<Trainee> fetchByParamId(@RequestParam int id) {
		Optional<Trainee> t = trs.fetchById(id);

		if (t.isPresent()) {
			return ResponseEntity.ok(t.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/trainees/byName")
	public List<Trainee> fetchByParamId(@RequestParam String name) {
		return trs.fetchByName(name);
	}
}