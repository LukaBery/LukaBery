package com.nhnacademy.daily.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Project {
    String code;
    LocalDate localDate;
    ProjectType type;
}
