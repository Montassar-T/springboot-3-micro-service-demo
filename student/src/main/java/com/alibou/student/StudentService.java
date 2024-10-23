package com.alibou.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final RestTemplate restTemplate;
    public void saveStudent(Student student) {
        // Call the School service to verify the school exists
        String schoolServiceUrl = "http://localhost:8222/api/v1/schools/" + student.getSchoolId(); // Use the gateway URL
        try {
            SchoolDTO school = restTemplate.getForObject(schoolServiceUrl, SchoolDTO.class);
            if (school != null) {   repository.save(student);
            }
        } catch (
                HttpClientErrorException.NotFound ex) {
            // Log the error or handle it as needed
            throw new RuntimeException("School does not exist with ID: " + student.getSchoolId(), ex);
        }
    }


    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }
}
