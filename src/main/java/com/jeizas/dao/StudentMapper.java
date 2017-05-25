package com.jeizas.dao;

import com.jeizas.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by jeizas on 5/25/17.
 */

@Mapper
@Component
public interface StudentMapper {

    @Select("select * from student where id = #{id}")
    Student findById(@Param("id") int id);
}
