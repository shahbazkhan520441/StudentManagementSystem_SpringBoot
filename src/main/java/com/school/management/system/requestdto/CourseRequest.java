package com.school.management.system.requestdto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {
    
    @NotNull(message = "Course name cannot be null")
    @NotBlank(message = "Course name cannot be blank")
    private String courseName;
    
    @NotNull(message = "Course description cannot be null")
    @NotBlank(message = "Course description cannot be blank")
    private String courseDescription;
}
