package net.javaguides.ems.controller;
import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.Employeedto;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.service.Employeeservice;
import net.javaguides.ems.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")


public class EmployeeController {

    private Employeeservice employeeService;
    //Add employee rest api
    @PostMapping
    public ResponseEntity<Employeedto> createEmployee(@RequestBody Employeedto employeeDto){
        Employeedto savedEmployee= employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<Employeedto> getEmployeeId( @PathVariable("id") Long employeeId) {
        Employeedto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);

    }
    //get all employees rest api
    @GetMapping
    public ResponseEntity<List<Employeedto>> getAllEmployees()
    {
        List<Employeedto> employees=employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    //update employee rest api
    @PutMapping("{id}")
    public ResponseEntity<Employeedto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody Employeedto updatedEmployee){
        Employeedto employeeDto= employeeService.updateEmployee(employeeId,updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    //delete employee rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<List<String>> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>("Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
