package project.spring.securitystudent.controller;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import project.spring.securitystudent.entity.Student;import java.util.Arrays;import java.util.List;@RestController@RequestMapping("/management/api/students")public class StudentManagementController {    private List<Student> students = Arrays.asList(            new Student(1, "nicolas"),            new Student(2, "dyana"),            new Student(3, "maria")    );    @GetMapping    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")    public List<Student> getAllStudents() {        System.out.println("getAllStudents");        return students;    }    @PostMapping    @PreAuthorize("hasAuthority('student: write')")    public void registerNewStudent(@RequestBody Student student) {        System.out.println("registerNewStudent");        System.out.println(student);    }    @DeleteMapping("/{id}")    @PreAuthorize("hasAuthority('student: write')")    public void deleteStudent(@PathVariable Integer id) {        System.out.println("deleteStudent");        System.out.println(id);    }    @PutMapping("/{id}")    @PreAuthorize("hasAuthority('student: write')")    private void updateStudent(@PathVariable Integer id, @RequestBody Student student) {        System.out.println("updateStudent");        System.out.println(String.format("%S %S", id, student));    }}