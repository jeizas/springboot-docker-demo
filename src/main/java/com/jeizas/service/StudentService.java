package com.jeizas.service;

import com.jeizas.dao.ElasticStudentRepository;
import com.jeizas.dao.StudentMapper;
import com.jeizas.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jeizas on 5/25/17.
 */

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ElasticStudentRepository elasticStudentRepository;

    public Student findByName(String name) {
        return elasticStudentRepository.findByName(name);
    }

}
