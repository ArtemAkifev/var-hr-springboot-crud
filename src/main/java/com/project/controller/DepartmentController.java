package com.project.controller;

import com.project.model.Department;
import com.project.model.Employee;
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

    @GetMapping("/departments/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable(required = false) String name) {
        Optional<Department> department = departmentRepository.findFirstByName(name);
        return department.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) {
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
            Department departmentNew = departmentRepository
                    .save(department);
            return new ResponseEntity<>(departmentNew, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department tutorial) {
        Optional<Department> departmentData = departmentRepository.findById(id);
        if (departmentData.isPresent()) {
            Department department = departmentData.get();
            department.setName(department.getName());
            return new ResponseEntity<>(departmentRepository.save(department), HttpStatus.OK);
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
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departmentData = departmentRepository.findAll();
        if (!departmentData.isEmpty()) {
            return new ResponseEntity<>(departmentData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/departments/{id}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesForDepartment(@PathVariable("id") long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return new ResponseEntity<>(department.get().getEmployees(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
