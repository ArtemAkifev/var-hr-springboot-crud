package com.project.controller;

import com.project.model.Department;
import com.project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;
    @GetMapping("/departments1")
    public ResponseEntity<List<Department>> getAllDepartments(@RequestParam(required = false) String title) {
        try {
            List<Department> departments = new ArrayList<Department>();
            if (title == null)
                departments.addAll(departmentRepository.findAll());
            else
                departments.addAll(departmentRepository.findByTitleContaining(title));
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getTutorialById(@PathVariable("id") long id) {
        Optional<Department> departmentData = departmentRepository.findById(id);
        if (departmentData.isPresent()) {
            return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        try {
            Department _department = departmentRepository
                    .save(new Department(department.getName_departments()));
            return new ResponseEntity<>(_department, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department tutorial) {
        Optional<Department> departmentData = departmentRepository.findById(id);
        if (departmentData.isPresent()) {
            Department _department = departmentData.get();
            _department.setName_departments(_department.getName_departments());
            return new ResponseEntity<>(departmentRepository.save(_department), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/department/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") long id) {
        try {
            departmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/departments")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            departmentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getTutorialById() {
        List<Department> departmentData = departmentRepository.findAll();
        if (!departmentData.isEmpty()) {
            return new ResponseEntity<>(departmentData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
