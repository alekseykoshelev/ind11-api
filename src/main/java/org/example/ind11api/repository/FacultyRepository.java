package org.example.ind11api.repository;

import org.example.ind11api.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findAllByColor(String color);

    Collection<Faculty> findAllByNameOrColorIgnoreCase(String name, String color);
}
