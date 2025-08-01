package net.employeeproject.ems.mapper;

import net.employeeproject.ems.dto.Departmentdto;
import net.employeeproject.ems.entity.Department;

public class DepartmentMapper {
    public static Departmentdto mapToDepartmentDto(Department dept) {
        return new Departmentdto(dept.getDeptid(), dept.getDeptname(),dept.getEmployees());
    }

    public static Department mapToDepartment(Departmentdto dto) {
        Department dept = new Department();
        dept.setDeptid(dto.getDeptid());
        dept.setDeptname(dto.getDeptname());
        return dept;
    }
}
