package org.example.ind11api.controller;

import org.example.ind11api.model.Faculty;
import org.example.ind11api.model.Student;
import org.example.ind11api.service.FacultyService;
import org.example.ind11api.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return service.update(student);
    }

    @DeleteMapping("/{id}")
    public Student remove(@PathVariable long id) {
        return service.remove(id);
    }

    @GetMapping("/{studentId}/faculty")
    public Faculty facultyByStudent(@PathVariable long studentId) {
        return service.get(studentId).getFaculty();
    }

    @GetMapping("/byAge")
    public Collection<Student> byAge(@RequestParam int age) {
        return service.filterByAge(age);
    }

    @GetMapping("/byAgeBetween")
    public Collection<Student> byAgeBetween(@RequestParam int min, @RequestParam int max) {
        return service.filterByAgeBetween(min, max);
    }

    @GetMapping("/count")
    public long getCountOfStudents() {
        return service.studentsCount();
    }

    @GetMapping("/avgAge")
    public double getAverageAge() {
        return service.averageAge();
    }

    @GetMapping("/lastfive")
    public Collection<Student> getLastFiveStudents() {
        return service.lastFiveStudents();
    }
}
