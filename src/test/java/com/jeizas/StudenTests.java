package com.jeizas;

import com.google.gson.Gson;
import com.jeizas.dao.StudentMapper;
import com.jeizas.entity.Student;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jeizas on 5/25/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudenTests {

    private final static Logger logger = Logger.getLogger(StudenTests.class);

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void findById() {
        Student u = studentMapper.findById(1);
        logger.info(new Gson().toJson(u));
        Assert.assertEquals(1, u.getId().intValue());
    }
}
