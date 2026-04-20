package com.example.patientlogging.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {
    private String name;
    private int age;
    private String diagnosis;
}
