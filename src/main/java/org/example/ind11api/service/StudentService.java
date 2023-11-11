package org.example.ind11api.service;

import org.example.ind11api.model.Student;
import org.example.ind11api.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        logger.info("Add method was invoked");
        return studentRepository.save(student);
    }

    public Student get(long id) {
        logger.info("Add method was invoked with argument {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    public Student remove(long id) {
        var entity = studentRepository.findById(id).orElse(null);
        if (entity != null) {
            studentRepository.delete(entity);
        }
        return entity;
    }

    public Student update(Student student) {
        return studentRepository.findById(student.getId())
                .map(entity -> studentRepository.save(student))
                .orElse(null);
    }

    public Collection<Student> filterByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> filterByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public long studentsCount() {
        try {
            // для примера обработки исключения
            if (true) {
                throw new RuntimeException("DB Error!");
            }
            return studentRepository.getStudentCount();
        } catch (RuntimeException e) {
            logger.error("Cannot get count of students!", e);
        }
        return -1;
    }

    public double averageAge() {
        logger.info("averageAge was invoked");
        return studentRepository.getAverageAge();
    }

    public Collection<Student> lastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }
}
