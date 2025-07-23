package net.javaguides.ems.service;

import net.javaguides.ems.dto.Departmentdto;
import java.util.List;

public interface DepartmentService {
    Departmentdto createDepartment(Departmentdto dto);
    List<Departmentdto> getAllDepartments();
    Departmentdto getDepartmentById(Long id);
}
