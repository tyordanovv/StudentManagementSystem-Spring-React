package com.yordanov.studentmanagementsystem.repository;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathRepository extends JpaRepository<Path, Long> {
}
