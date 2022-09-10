package com.yordanov.studentmanagementsystem.requestBodies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateSubject {
    private String name;
    private String description;
    private int[] teachers;
}
