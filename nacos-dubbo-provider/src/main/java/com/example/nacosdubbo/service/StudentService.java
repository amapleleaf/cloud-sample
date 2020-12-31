package com.example.nacosdubbo.service;

import com.sample.cloud.serviceapi.api.IStudentService;
import com.sample.cloud.serviceapi.api.Student;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService(timeout = 60000)
public class StudentService implements IStudentService {
    @Override
    public Student queryByIdno(String idNo) {
        Student student = new Student();
        student.setIdNo("NO"+idNo);
        student.setAge(13);
        student.setName("张三丰");
        student.setGrade(8);
        student.setClazz(2);
        return student;
    }
    @Override
    public List<Student> queryByGrade(int grade, int clazz){
        List<Student> studentList = new ArrayList<>();
        for (int i =0;i<2;i++){
            Student student = new Student();
            student.setIdNo("NO"+i);
            student.setAge(13);
            student.setName("张三丰"+i);
            student.setGrade(grade);
            student.setClazz(clazz);
        }
        return studentList;
    }
}
