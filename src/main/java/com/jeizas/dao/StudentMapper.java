package com.jeizas.dao;

import com.jeizas.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jeizas on 5/25/17.
 */

@Mapper
@Component
public interface StudentMapper {

    @Select("select * from student where id = #{id}")
    Student findById(@Param("id") int id);

    @Select("select * from student")
    List<Student> findAll();

    @Insert("insert into student(name, age) values(#{name}, #{age})")
    int save(Student student);

    @Update("UPDATE student SET name=#{name} WHERE id =#{id}")
    int update(@Param("id") int id, @Param("name") String name);

    @Delete("DELETE FROM student WHERE id =#{id}")
    void delete(int id);
}
