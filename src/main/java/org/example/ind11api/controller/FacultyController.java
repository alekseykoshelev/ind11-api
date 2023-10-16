package org.example.ind11api.controller;

import org.example.ind11api.dto.FacultyDTO;
import org.example.ind11api.dto.StudentDTO;
import org.example.ind11api.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public FacultyDTO add(@RequestBody FacultyDTO faculty) {
        return service.add(faculty);
    }

    @GetMapping("/{id}")
    public FacultyDTO get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public FacultyDTO update(@RequestBody FacultyDTO faculty) {
        return service.update(faculty);
    }

    @DeleteMapping("/{id}")
    public FacultyDTO remove(@PathVariable long id) {
        return service.remove(id);
    }

    @GetMapping("/{facultyId}/students")
    public Collection<StudentDTO> findByFaculty(@PathVariable long facultyId) {
        return service.getStudents(facultyId);
    }

    @GetMapping("/byNameOrColor")
    public Collection<FacultyDTO> byNameOrColor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color
    ) {
        return service.filterByNameOrColor(name, color);
    }
}
