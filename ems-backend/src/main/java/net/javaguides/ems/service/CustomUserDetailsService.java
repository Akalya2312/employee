package net.javaguides.ems.service;

import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.repository.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Employeerepo employeerepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp = employeerepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(emp.getEmail(), emp.getPassword(), new ArrayList<>());
    }
}
