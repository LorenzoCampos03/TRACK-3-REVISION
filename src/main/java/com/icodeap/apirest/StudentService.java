package com.icodeap.apirest;

import com.icodeap.apirest.model.Student;
import com.icodeap.apirest.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student){
        logger.info("Invocar: Registrar estudiante con DNI: {}", student.getDni());
        Student savedStudent = studentRepository.save(student);
        logger.info("Estudiante registrado exitosamente: {}", savedStudent.getDni());
        return savedStudent;
    }

    public Student getStudentById(String dni){
        logger.info("Invocar: Buscar estudiante por DNI: {}", dni);
        Optional<Student> optionalStudent = studentRepository.findById(dni);
        if (optionalStudent.isPresent()) {
            logger.info("Estudiante encontrado: {}", dni);
            return optionalStudent.get();
        } else {
            logger.warn("Estudiante no encontrado: {}", dni);
            return null;
        }
    }
    
    public List<Student> getAllStudents(){
        logger.info("Invocar: Obtener todos los estudiantes");
        List<Student> students = studentRepository.findAll();
        logger.info("Total de estudiantes encontrados: {}", students.size());
        return students;
    }

    public Student updateStudent(String dni, Student student){
        logger.info("Invocar: Actualizar estudiante con DNI: {}", dni);
        if (studentRepository.existsById(dni)) {
            student.setDni(dni);
            Student updatedStudent = studentRepository.save(student);
            logger.info("Estudiante actualizado exitosamente: {}", dni);
            return updatedStudent;
        } else {
            logger.warn("Estudiante no encontrado para actualizar: {}", dni);
            return null;
        }
    }

    public void deleteStudent(String dni){
        logger.info("Invocar: Eliminar estudiante con DNI: {}", dni);
        studentRepository.deleteById(dni);
        logger.info("Estudiante eliminado exitosamente: {}", dni);
    }
}
