package org.example.ind11api.service;

import org.example.ind11api.dto.FacultyDTO;
import org.example.ind11api.dto.StudentDTO;
import org.example.ind11api.mapper.FacultyMapper;
import org.example.ind11api.mapper.StudentMapper;
import org.example.ind11api.repository.FacultyRepository;
import org.example.ind11api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final FacultyMapper facultyMapper;
    private final StudentMapper studentMapper;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository,
                          FacultyMapper facultyMapper,
                          StudentMapper studentMapper) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.facultyMapper = facultyMapper;
        this.studentMapper = studentMapper;
    }

    public FacultyDTO add(FacultyDTO faculty) {
        return facultyMapper.toDto(facultyRepository.save(facultyMapper.toEntity(faculty)));
    }

    public FacultyDTO get(long id) {
        return facultyRepository.findById(id).map(facultyMapper::toDto).orElse(null);
    }

    public FacultyDTO remove(long id) {
        var entity = facultyRepository.findById(id).orElse(null);
        if (entity != null) {
            facultyRepository.delete(entity);
            return facultyMapper.toDto(entity);
        }
        return null;
    }

    public FacultyDTO update(FacultyDTO facultyDTO) {
        return facultyRepository.findById(facultyDTO.getId())
                .map(entity -> facultyRepository.save(facultyMapper.toEntity(facultyDTO)))
                .map(facultyMapper::toDto)
                .orElse(null);
    }

    public Collection<FacultyDTO> filterByNameOrColor(String name, String color) {
        return facultyRepository.findAllByNameOrColorIgnoreCase(name, color)
                .stream()
                .map(facultyMapper::toDto)
                .collect(Collectors.toList());
    }

    public Collection<StudentDTO> getStudents(long facultyId) {
        return studentRepository.findAllByFaculty_Id(facultyId).stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }
}
