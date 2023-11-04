package org.example.ind11api.repository;

import org.example.ind11api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findAllByAgeBetween(int min, int max);

    @Query(value = "select count(*) from student", nativeQuery = true)
    long getStudentCount();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    double getAverageAge();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    Collection<Student> getLastFiveStudents();
}
