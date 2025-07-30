package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.Certificatedto;
import net.javaguides.ems.dto.Employeedto;
import net.javaguides.ems.exception.BadRequestException;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.payload.ApiResponse;
import net.javaguides.ems.repository.Employeerepo;
import net.javaguides.ems.security.JwtUtil;
import net.javaguides.ems.service.Employeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private Employeeservice employeeService;

    @Autowired
    private Employeerepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public final String EMPLOYEE_CREATED_SUCCESSFULLY="Employee Created Successfully";
    public final String EMPLOYEE_FETCHED_SUCCESSFULLY="Employee Fetched Successfully";
    public final String ALL_EMPLOYEES_FETCHED="All Employees fetched";
    public final String EMPLOYEE_UPDATED_SUCCESSFULLY="Employee Updated Successfully";
    public final String EMPLOYEE_DELETED_SUCCESSFULLY="Employee deleted Successfully";
    public final String CERTIFICATES_ADDED_SUCCESSFULLY="Certificates added Successfully";
    public final String VALIDATION_FAILED="Validation failed";
    public final String UNEXCEPTED_ERROR="Unexcepted error";

    // Add employee
    @PostMapping
    public ResponseEntity<ApiResponse<Employeedto>> createEmployee(@RequestBody Employeedto employeeDto) {
        Employeedto savedEmployee = employeeService.createEmployee(employeeDto);
        ApiResponse<Employeedto> response = new ApiResponse<>(
                "EMPLOYEE_CREATED_SUCCESSFULLY",
                savedEmployee,
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // Get employee by token
    @GetMapping("/getemp")
    public ResponseEntity<ApiResponse<Employeedto>> getEmployeeId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer "
        Long empId = jwtUtil.extractEmpId(token);
        Employeedto employeeDto = employeeService.getEmployeeById(empId);
        ApiResponse<Employeedto> response = new ApiResponse<>(
                EMPLOYEE_FETCHED_SUCCESSFULLY,
                employeeDto,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<ApiResponse<List<Employeedto>>> getAllEmployees() {
        List<Employeedto> employees = employeeService.getAllEmployees();
        ApiResponse<List<Employeedto>> response = new ApiResponse<>(
                ALL_EMPLOYEES_FETCHED,
                employees,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    // Update employee
    @PutMapping("/putemp")
    public ResponseEntity<ApiResponse<Employeedto>> updateEmployee(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Employeedto updatedEmployee) {

        String token = authHeader.substring(7);
        Long empId = jwtUtil.extractEmpId(token);
        Employeedto employeeDto = employeeService.updateEmployee(empId, updatedEmployee);

        ApiResponse<Employeedto> response = new ApiResponse<>(
                EMPLOYEE_UPDATED_SUCCESSFULLY,
                employeeDto,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    // Delete employee
    @DeleteMapping("/delemp")
    public ResponseEntity<ApiResponse<Object>> deleteEmployee(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Long empId = jwtUtil.extractEmpId(token);
        employeeService.deleteEmployee(empId);

        ApiResponse<Object> response = new ApiResponse<>(
                EMPLOYEE_DELETED_SUCCESSFULLY,
                null,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }
    @PostMapping("/add-certificates")
    public ResponseEntity<ApiResponse<Employeedto>> addCertificates(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody List<Certificatedto> certificateDtos) {

        String token = authHeader.substring(7); // Remove "Bearer "
        Long empId = jwtUtil.extractEmpId(token);

        Employeedto updatedEmployee = employeeService.addCertificatesToEmployee(empId, certificateDtos);

        ApiResponse<Employeedto> response = new ApiResponse<>(
                CERTIFICATES_ADDED_SUCCESSFULLY,
                updatedEmployee,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    // ------------------- Exception Handlers -------------------

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                ex.getMessage(),
                null,
                false,
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleBadRequest(BadRequestException ex) {
        ApiResponse<List<String>> response = new ApiResponse<>(
                VALIDATION_FAILED,
                ex.getErrors(),
                false,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleOtherExceptions(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                "UNEXCEPTED_ERROR:" + ex.getMessage(),
                null,
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
