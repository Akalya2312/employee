package net.employeeproject.ems.controller;

import lombok.RequiredArgsConstructor;
import net.employeeproject.ems.dto.Departmentdto;
import net.employeeproject.ems.payload.ApiResponse;
import net.employeeproject.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    public final String DEPARTMENT_CREATED_SUCCESSFULLY="Department created successfully";
    public final String DEPARTMENT_RETRIEVED_SUCCESSFULLY="Department retrieved successfully";
    public final String SPECIFIC_DEPARTMENT_RETRIEVED_SUCCESSFULLY="Specific Department retrieved successfully";

    @PostMapping
    public ResponseEntity<ApiResponse<Departmentdto>> create(@RequestBody Departmentdto dto) {
        Departmentdto created = departmentService.createDepartment(dto);
        ApiResponse<Departmentdto> response = new ApiResponse<>(
                DEPARTMENT_CREATED_SUCCESSFULLY,
                created,
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Departmentdto>>> getAll() {
        List<Departmentdto> departments = departmentService.getAllDepartments();
        ApiResponse<List<Departmentdto>> response = new ApiResponse<>(
                DEPARTMENT_RETRIEVED_SUCCESSFULLY,
                departments,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Departmentdto>> getById(@PathVariable Long id) {
        Departmentdto dto = departmentService.getDepartmentById(id);
        ApiResponse<Departmentdto> response = new ApiResponse<>(
                SPECIFIC_DEPARTMENT_RETRIEVED_SUCCESSFULLY,
                dto,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }
}
