package org.example.ind11api.repository;

import org.example.ind11api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findAllByAgeBetween(int min, int max);
}
