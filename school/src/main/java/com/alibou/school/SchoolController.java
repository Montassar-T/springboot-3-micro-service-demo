package com.alibou.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody School school
    ) {
        service.saveSchool(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(service.findAllSchools());
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllSchools(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));
    }

    @GetMapping("/{id}") // New endpoint for retrieving a school by ID
    public ResponseEntity<?> findSchoolById(@PathVariable Integer id) {
        School school = service.findSchoolById(id); // Assuming this method is implemented in the service
        if (school == null) {
            // Return a custom error message as a Map
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "School does not exist with ID: " + id));
        }
        return ResponseEntity.ok(school);
    }



}
