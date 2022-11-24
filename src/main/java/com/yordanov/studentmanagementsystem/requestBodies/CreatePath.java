package com.yordanov.studentmanagementsystem.requestBodies;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Getter
@RequiredArgsConstructor
public class CreatePath {
    private String description;
    private String name;
    private String type;
    private HashMap<String, int[]> semesterSubjects;
}
