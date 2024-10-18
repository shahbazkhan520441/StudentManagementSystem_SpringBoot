package com.school.management.system.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherRequest {
    @NotNull(message = "teacherName cannot be null")
    @NotBlank(message = "teacherName cannot be blank")
    private String teacherName;

    @Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email")
    @NotBlank(message = "Email cannot be blank")
    private String teacherEmail;

    @NotNull(message = "teacherAddress cannot be null")
    @NotBlank(message = "teacherAddress cannot be blank")
    private String teacherAddress;
}
