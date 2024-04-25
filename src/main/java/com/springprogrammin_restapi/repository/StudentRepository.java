package com.springprogrammin_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springprogrammin_restapi.entity.Student;

public interface StudentRepository  extends JpaRepository<Student, Integer>{

}
