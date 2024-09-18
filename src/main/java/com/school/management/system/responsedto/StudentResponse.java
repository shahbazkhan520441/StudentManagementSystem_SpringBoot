package com.school.management.system.responsedto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private int studentId;
    private String studentName;
    private String studentEmail;
    private String studentAddress;

    private List<Integer> courseIds; // New field for course IDs
}
