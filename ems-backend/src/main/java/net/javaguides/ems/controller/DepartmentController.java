package net.javaguides.ems.controller;

import lombok.RequiredArgsConstructor;

import net.javaguides.ems.dto.Departmentdto;
import net.javaguides.ems.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Departmentdto> create(@RequestBody Departmentdto dto) {
        return ResponseEntity.ok(departmentService.createDepartment(dto));
    }

    @GetMapping
    public ResponseEntity<List<Departmentdto>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departmentdto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }
}
