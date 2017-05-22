package com.jeizas.controller;

import com.jeizas.dao.StudentRepository;
import com.jeizas.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeizas on 2017/1/1.
 */
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    Map<String, Object> retMap = new HashMap<>();

    /**
     * 查询所有
     * @return
     */
    @GetMapping(value = "stus")
    public Map<String, Object> studentList() {
        retMap.put("list", studentRepository.findAll());
        return retMap;
    }

    /**
     * 增加数据
     * @param age
     * @param name
     * @return
     */
    @PostMapping(value = "stu/add")
    public Map<String, Object> addStudent(@RequestParam("age") Integer age, @RequestParam("name") String name) {
        Student stu = new Student();
        stu.setAge(age);
        stu.setName(name);
        retMap.put("error", studentRepository.save(stu));
        return retMap;
    }

    /**
     * 通过ID查询记录
     * @param id
     * @return
     */
    @GetMapping(value = "stu/{id}")
    public Map<String, Object> findStudentById(@PathVariable("id") Integer id) {
        retMap.put("obj", studentRepository.findOne(id));
        return retMap;
    }

    /**
     * 删除一条记录
     * @param id
     * @return
     */
    @DeleteMapping(value = "stu/del/{id}")
    public Map<String, Object> delStudentById(@PathVariable("id") Integer id) {
        studentRepository.delete(id);
        retMap.put("obj", studentRepository.findAll());
        return retMap;
    }

    /**
     * 更新数据
     * @param id
     * @param name
     * @return
     */
    @Transactional //update and delete operations need this annotation
    @PutMapping(value = "stu/update")
    public Map<String, Object> updateStudent(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        String msg = "成功！";
        Student stu = studentRepository.findOne(id);
        if (stu != null && name != null) {
            stu.setName(name);
            retMap.put("obj", studentRepository.updateStudent(name, id));
        } else {
            msg = "参数错误！";
        }
        retMap.put("msg", msg);
        return retMap;
    }
}
