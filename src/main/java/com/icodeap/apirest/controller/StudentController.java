package com.icodeap.apirest.controller;

import com.icodeap.apirest.StudentService;
import com.icodeap.apirest.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student created = studentService.createStudent(student);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Student> searchStudentByDni(@PathVariable("dni") String dni){
        Student student = studentService.getStudentById(dni);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{dni}")
    public ResponseEntity<Student> updateStudent(@PathVariable("dni") String dni, @RequestBody Student student){
        Student updated = studentService.updateStudent(dni, student);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteStudentByDni(@PathVariable("dni") String dni){
        studentService.deleteStudent(dni);
        return ResponseEntity.noContent().build();
    }

}
