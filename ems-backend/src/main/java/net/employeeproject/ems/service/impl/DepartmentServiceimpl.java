package net.employeeproject.ems.service.impl;

import lombok.RequiredArgsConstructor;
import net.employeeproject.ems.dto.Departmentdto;
import net.employeeproject.ems.entity.Department;
import net.employeeproject.ems.mapper.DepartmentMapper;
import net.employeeproject.ems.repository.Departmentrepo;
import net.employeeproject.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceimpl implements DepartmentService {

    private final Departmentrepo deptRepo;

    @Override
    public Departmentdto createDepartment(Departmentdto dto) {
        Department dept = DepartmentMapper.mapToDepartment(dto);
        Department saved = deptRepo.save(dept);
        return DepartmentMapper.mapToDepartmentDto(saved);
    }

    @Override
    public List<Departmentdto> getAllDepartments() {
        return deptRepo.findAll().stream()
                .map(DepartmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Departmentdto getDepartmentById(Long id) {
        Department dept = deptRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return DepartmentMapper.mapToDepartmentDto(dept);
    }
}
