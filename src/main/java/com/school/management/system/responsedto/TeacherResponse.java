package com.school.management.system.responsedto;


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
public class TeacherResponse {
    private int teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherAddress;
}
