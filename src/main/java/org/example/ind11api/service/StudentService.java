package org.example.ind11api.service;

import org.example.ind11api.dto.FacultyDTO;
import org.example.ind11api.dto.StudentDTO;
import org.example.ind11api.mapper.FacultyMapper;
import org.example.ind11api.mapper.StudentMapper;
import org.example.ind11api.model.Student;
import org.example.ind11api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final FacultyMapper facultyMapper;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper,
                          FacultyMapper facultyMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.facultyMapper = facultyMapper;
    }

    public StudentDTO add(StudentDTO studentDTO) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentDTO)));
    }

    public StudentDTO get(long id) {
        return studentRepository.findById(id).map(studentMapper::toDto).orElse(null);
    }

    public StudentDTO remove(long id) {
        return studentRepository.findById(id)
                .map(entity -> {
                    studentRepository.delete(entity);
                    return studentMapper.toDto(entity);
                })
                .orElse(null);
    }

    public StudentDTO update(StudentDTO studentDTO) {
        return studentRepository.findById(studentDTO.getId())
                .map(entity -> studentRepository.save(studentMapper.toEntity(studentDTO)))
                .map(studentMapper::toDto)
                .orElse(null);
    }

    public Collection<StudentDTO> filterByAge(int age) {
        return studentRepository.findByAge(age).stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public Collection<StudentDTO> filterByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max).stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public FacultyDTO findStudentFaculty(long studentId) {
        return studentRepository.findById(studentId)
                .map(Student::getFaculty)
                .map(facultyMapper::toDto)
                .orElse(null);
    }
}
