package com.springprogrammin_restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springprogrammin_restapi.entity.Student;
import com.springprogrammin_restapi.repository.StudentRepository;

@RestController
public class StudentControlller 
{
	//get all student
	@Autowired
	StudentRepository repo;
	
	//localhost:8080/students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		List<Student> students=repo.findAll();
		return students;
	}
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id)
	{
		Student students=repo.findById(id).get();
		return students;

	}
	
	
	@PostMapping("/students/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createStudent(@RequestBody Student students)
	{
		repo.save(students);
		return "Data inserted successfully";
	}
	
	@PutMapping("/students/update/{id}")
	public String updateStudentById(@PathVariable int id,@RequestBody Student students)
	{
		Optional<Student> stud=repo.findById(id);
		if(stud.isPresent())
		{
			Student existstu=stud.get();
			existstu.setBranch(students.getBranch());
			existstu.setRollno(students.getRollno());
			existstu.setName(students.getName());
			existstu.setPercentage(students.getPercentage());
			
			repo.save(existstu);
			return "Student Details against Id" + id +"updated.";
			
		}else {
			return "Student Details doen not exist for student Id" +id;

		}
		
	}
	
	
	@DeleteMapping("/students/{id}")
	public String deleteStudentById(@PathVariable int id)
	{
		repo.deleteById(id);
		return "Student data deleted Successfully";
	}
	

}
