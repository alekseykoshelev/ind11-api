package org.example.ind11api.controller;

import org.example.ind11api.dto.FacultyDTO;
import org.example.ind11api.dto.StudentDTO;
import org.example.ind11api.model.Faculty;
import org.example.ind11api.model.Student;
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
    public StudentDTO add(@RequestBody StudentDTO student) {
        return service.add(student);
    }

    @GetMapping("/{id}")
    public StudentDTO get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public StudentDTO update(@RequestBody StudentDTO student) {
        return service.update(student);
    }

    @DeleteMapping("/{id}")
    public StudentDTO remove(@PathVariable long id) {
        return service.remove(id);
    }

    @GetMapping("/{studentId}/faculty")
    public FacultyDTO facultyByStudent(@PathVariable long studentId) {
        return service.findStudentFaculty(studentId);
    }

    @GetMapping("/byAge")
    public Collection<StudentDTO> byAge(@RequestParam int age) {
        return service.filterByAge(age);
    }

    @GetMapping("/byAgeBetween")
    public Collection<StudentDTO> byAgeBetween(@RequestParam int min, @RequestParam int max) {
        return service.filterByAgeBetween(min, max);
    }
}
