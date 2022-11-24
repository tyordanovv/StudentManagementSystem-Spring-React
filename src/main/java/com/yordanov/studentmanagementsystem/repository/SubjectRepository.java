package com.yordanov.studentmanagementsystem.repository;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);

    List<Subject> findAllByTakenIsFalse();

    @Query("select s from Subject s join User u where u.username = :username")
    List<Subject> findAllByUsername(@Param("teacherUsername") String username);
}
