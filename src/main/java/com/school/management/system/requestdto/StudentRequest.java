package com.school.management.system.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {
	
	@NotNull(message = "studentName can not be null")
	@NotBlank(message = "studentName can not be blank")
	private String studentName;
	
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	@NotBlank(message = "Email can not be blank")
	private String studentEmail;
	
	@NotNull(message = "studentAddress  can not be null")
	@NotBlank(message = "studentAddress can not be blank")
	private String studentAddress;

}
