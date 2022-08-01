package com.yordanov.studentmanagementsystem.repository;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
