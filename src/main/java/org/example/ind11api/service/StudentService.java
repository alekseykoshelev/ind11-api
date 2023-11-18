package org.example.ind11api.service;

import org.example.ind11api.model.Student;
import org.example.ind11api.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

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

    public Collection<String> getStudentsNameStartA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double streamAverageAge() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getAge)
                .mapToInt(o -> o)
                .average()
                .orElse(0d);
    }

    public void printNonSync() {
        var students = studentRepository.findAll();
        System.out.println(students.get(0));
        System.out.println(students.get(1));

        Thread t1 = new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        });
        Thread t2 = new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        });
        t2.start();
        t1.start();
        System.out.println("-----------------");
    }

    public void printSync() {
        var students = studentRepository.findAll();
        printSynchronized(students.get(0));
        printSynchronized(students.get(1));

        Thread t1 = new Thread(() -> {
            printSynchronized(students.get(2));
            printSynchronized(students.get(3));
        });
        Thread t2 = new Thread(() -> {
            printSynchronized(students.get(4));
            printSynchronized(students.get(5));
        });
        t2.start();
        t1.start();
        System.out.println("-----------------");
    }

    private synchronized void printSynchronized(Object o) {
        System.out.println(o.toString());
    }
}
