package org.example.ind11api.mapper;

import org.example.ind11api.dto.FacultyDTO;
import org.example.ind11api.model.Faculty;
import org.springframework.stereotype.Service;

@Service
public class FacultyMapper implements EntityMapper<Faculty, FacultyDTO> {

    public Faculty toEntity(FacultyDTO dto) {
        Faculty faculty = new Faculty();
        faculty.setName(dto.getName());
        faculty.setColor(dto.getColor());
        return faculty;
    }

    @Override
    public FacultyDTO toDto(Faculty entity) {
        FacultyDTO response = new FacultyDTO();
        response.setId(entity.getId());
        response.setColor(entity.getColor());
        response.setName(entity.getName());
        return response;
    }
}
