package org.example.ind11api.mapper;

import org.example.ind11api.dto.StudentDTO;
import org.example.ind11api.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper implements EntityMapper<Student, StudentDTO> {

    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        return student;
    }

    @Override
    public StudentDTO toDto(Student entity) {
        StudentDTO response = new StudentDTO();
        response.setId(entity.getId());
        response.setAge(entity.getAge());
        response.setName(entity.getName());
        return response;
    }
}
