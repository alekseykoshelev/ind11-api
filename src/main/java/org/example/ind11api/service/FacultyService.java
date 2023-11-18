package org.example.ind11api.service;

import org.example.ind11api.model.Faculty;
import org.example.ind11api.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {

    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty get(long id) {
        return repository.findById(id).orElse(null);
    }

    public Faculty remove(long id) {
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
            return entity;
        }
        return null;
    }

    public Faculty update(Faculty faculty) {
        return repository.findById(faculty.getId())
                .map(entity -> repository.save(faculty))
                .orElse(null);
    }

    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        return repository.findAllByNameOrColorIgnoreCase(name, color);
    }

    public String getTheLongestFacultyName() {
        return repository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
