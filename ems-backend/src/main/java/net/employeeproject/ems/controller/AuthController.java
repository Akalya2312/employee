package net.employeeproject.ems.controller;

import net.employeeproject.ems.entity.Employee;
import net.employeeproject.ems.exception.ResourceNotFoundException;
import net.employeeproject.ems.payload.ApiResponse;
import net.employeeproject.ems.repository.Employeerepo;
import net.employeeproject.ems.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    private Employeerepo employeerepo;
    public final String LOGIN_SUCCESSFUL="Login Successful";
    public final String INVALID_USERNAME_OR_PASSWORD="Invalid username or password";
    public final String REFRESH_TOKEN_IS_EXPIRED="Refresh token is expired";
    public final String ACCESS_TOKEN_REFRESHED_SUCCESSFULLY="Access token refreshed successfully";
    public final String INVALID_REFRESH_TOKEN="Invalid refresh token";

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody Employee emp) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(emp.getEmail(), emp.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Employee emp1 = employeerepo.findByEmail(emp.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

            String accessToken = jwtutil.generateToken(userDetails, emp1);
            String refreshToken = jwtutil.generateRefreshToken(userDetails, emp1);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);

            ApiResponse<Map<String, String>> response = new ApiResponse<>(
                    LOGIN_SUCCESSFUL,
                    tokens,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ApiResponse<Map<String, String>> response = new ApiResponse<>(
                    INVALID_USERNAME_OR_PASSWORD,
                    null,
                    false,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<Map<String, String>>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        try {
            String username = jwtutil.extractUsername(refreshToken);
            Long empId = jwtutil.extractEmpId(refreshToken);

            if (jwtutil.isTokenExpired(refreshToken)) {
                ApiResponse<Map<String, String>> response = new ApiResponse<>(
                        REFRESH_TOKEN_IS_EXPIRED,
                        null,
                        false,
                        HttpStatus.UNAUTHORIZED.value()
                );
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

            Employee emp = employeerepo.findById(empId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    username, emp.getPassword(), new ArrayList<>());

            String newAccessToken = jwtutil.generateToken(userDetails, emp);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", newAccessToken);
            tokens.put("refreshToken", refreshToken);

            ApiResponse<Map<String, String>> response = new ApiResponse<>(
                    ACCESS_TOKEN_REFRESHED_SUCCESSFULLY,
                    tokens,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ApiResponse<Map<String, String>> response = new ApiResponse<>(
                    INVALID_REFRESH_TOKEN,
                    null,
                    false,
                    HttpStatus.UNAUTHORIZED.value()
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
