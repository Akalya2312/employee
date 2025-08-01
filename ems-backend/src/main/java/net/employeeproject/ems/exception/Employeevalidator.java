package net.employeeproject.ems.exception;
import net.employeeproject.ems.dto.Employeedto;
import net.employeeproject.ems.repository.Employeerepo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Employeevalidator extends RuntimeException{
  public static void validate(Employeedto employeeDto, Employeerepo repo ,boolean isUpdate,Long employeeId) {

    List<String> validationErrors = new ArrayList<>();
    if (employeeDto.getEmpname() == null || employeeDto.getEmpname().trim().isEmpty()) {
      validationErrors.add("Name cannot be empty.");
    }
    if (employeeDto.getEmail() == null || employeeDto.getEmail().trim().isEmpty()) {
      validationErrors.add("Email cannot be empty.");
    }
    if (employeeDto.getPhno() == null) {
      validationErrors.add("Phone number cannot be empty.");
    }
    if (employeeDto.getPassword() == null || employeeDto.getPassword().trim().isEmpty()) {
      validationErrors.add("Password cannot be empty.");
    }

    // 1. Duplicate email check
    repo.findByEmail(employeeDto.getEmail()).ifPresent(existing->{if(!isUpdate||!existing.getEmpid().equals(employeeId)) {
      validationErrors.add("Email already exists.");
    }});

    // 2. Email format check
    String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail.com+$";
    if (!Pattern.matches(emailRegex, employeeDto.getEmail())) {
      validationErrors.add("Invalid email format.");
    }

    // 3. Phone format check
    String phoneRegex = "\\d{10}";
    if (!Pattern.matches(phoneRegex, String.valueOf(employeeDto.getPhno()))) {
      validationErrors.add("Phone number must be 10 digits.");
    }

    if (!validationErrors.isEmpty()) {
      throw new BadRequestException(validationErrors);
    }

  }

}

