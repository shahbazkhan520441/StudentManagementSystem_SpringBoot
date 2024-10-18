package com.school.management.system.requestdto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {

    @NotNull(message = "studentName cannot be null")
    @NotBlank(message = "studentName cannot be blank")
    private String studentName;

    @Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email")
    @NotBlank(message = "Email cannot be blank")
    private String studentEmail;

    @NotNull(message = "studentAddress cannot be null")
    @NotBlank(message = "studentAddress cannot be blank")
    private String studentAddress;

    @NotNull(message = "courseIds cannot be null")
    private List<Integer> courseIds; // New field for course IDs

}
