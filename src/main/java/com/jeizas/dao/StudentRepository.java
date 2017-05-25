//package com.jeizas.dao;
//
//import com.jeizas.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// * Created by jeizas on 2017/1/1.
// */
//public interface StudentRepository extends JpaRepository<Student, Integer>{
//
//    @Modifying
//    @Query(value = "update Student s set s.name=?1 where s.id=?2")
//    Integer updateStudent(String name, Integer id);
//}
