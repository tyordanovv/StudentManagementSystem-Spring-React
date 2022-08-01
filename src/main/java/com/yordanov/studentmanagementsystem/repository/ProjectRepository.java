package com.yordanov.studentmanagementsystem.repository;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
