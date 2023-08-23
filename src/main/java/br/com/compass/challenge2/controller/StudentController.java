package br.com.compass.challenge2.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import br.com.compass.challenge2.dto.StudentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.challenge2.entity.Student;
import br.com.compass.challenge2.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<StudentDTO>> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student(studentDTO.id(), studentDTO.name(), studentDTO.email(),
                studentDTO.group(), studentDTO.squad(), null, null, null);

        Student createdStudent = studentService.save(student);

        StudentDTO createdStudentDTO = new StudentDTO(createdStudent.getId(), createdStudent.getName(),
                createdStudent.getEmail(), createdStudent.getGroup(), createdStudent.getSquad());

        EntityModel<StudentDTO> studentEntityModel = EntityModel.of(createdStudentDTO,
                linkTo(methodOn(StudentController.class).findStudentById(createdStudent.getId())).withSelfRel(),
                linkTo(methodOn(StudentController.class).updateStudent(createdStudent.getId(), studentDTO))
                        .withRel("update"),
                linkTo(methodOn(StudentController.class).deleteStudent(createdStudent.getId())).withRel("delete"),
                linkTo(methodOn(AssessmentController.class).getAssessmentsByStudentId(createdStudent.getId()))
                        .withRel("assessments"));

        return new ResponseEntity<>(studentEntityModel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<StudentDTO>>> findAllStudents() {
        List<Student> students = studentService.findAll();

        List<EntityModel<StudentDTO>> studentDTOs = students.stream().map(s -> {
            StudentDTO studentDTO = new StudentDTO(s.getId(), s.getName(), s.getEmail(), s.getGroup(), s.getSquad());

            return EntityModel.of(studentDTO,
                    linkTo(methodOn(StudentController.class).findStudentById(s.getId())).withSelfRel(),
                    linkTo(methodOn(StudentController.class).updateStudent(s.getId(), studentDTO))
                            .withRel("update"),
                    linkTo(methodOn(StudentController.class).deleteStudent(s.getId())).withRel("delete"),
                    linkTo(methodOn(AssessmentController.class).getAssessmentsByStudentId(s.getId()))
                            .withRel("assessments"));

        }).toList();

        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> findStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(),
                student.getEmail(), student.getGroup(), student.getSquad());

        EntityModel<StudentDTO> studentEntityModel = EntityModel.of(studentDTO,
                linkTo(methodOn(StudentController.class).findStudentById(id)).withSelfRel(),
                linkTo(methodOn(StudentController.class).updateStudent(id, studentDTO)).withRel("update"),
                linkTo(methodOn(StudentController.class).deleteStudent(id)).withRel("delete"),
                linkTo(methodOn(AssessmentController.class).getAssessmentsByStudentId(student.getId()))
                        .withRel("assessments"),
                linkTo(methodOn(StudentController.class).findAllStudents()).withRel("all_students"));

        return new ResponseEntity<>(studentEntityModel, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> updateStudent(@PathVariable Long id,
                                                                 @RequestBody StudentDTO studentDTO) {

        Student student = new Student();
        student.setId(id);
        student.setName(studentDTO.name());
        student.setEmail(studentDTO.email());
        student.setGroup(studentDTO.group());
        student.setSquad(studentDTO.squad());
        Student updatedStudent = studentService.update(student);
        StudentDTO updatedStudentDTO = new StudentDTO(updatedStudent.getId(), updatedStudent.getName(),
                updatedStudent.getEmail(), updatedStudent.getGroup(), updatedStudent.getSquad());

        EntityModel<StudentDTO> studentEntityModel = EntityModel.of(updatedStudentDTO,
                linkTo(methodOn(StudentController.class).findStudentById(id)).withSelfRel(),
                linkTo(methodOn(StudentController.class).findAllStudents()).withRel("all_students"));

        return new ResponseEntity<>(studentEntityModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> deleteStudent(@PathVariable Long id) {
        Student deletedStudent = studentService.deleteById(id);
        StudentDTO deletedStudentDTO = new StudentDTO(deletedStudent.getId(), deletedStudent.getName(),
                deletedStudent.getEmail(), deletedStudent.getGroup(), deletedStudent.getSquad());

        EntityModel<StudentDTO> studentEntityModel = EntityModel.of(deletedStudentDTO,
                linkTo(methodOn(StudentController.class).findAllStudents()).withRel("all_students"));

        return new ResponseEntity<>(studentEntityModel, HttpStatus.OK);
    }
}
