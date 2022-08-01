package com.yordanov.studentmanagementsystem.repository;

import com.yordanov.studentmanagementsystem.model.relation.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProjectRepository extends JpaRepository<UserProject, Long> {
}