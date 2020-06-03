package com.example.nacosdubboclient.controller;

import com.sample.cloud.serviceapi.api.IStudentService;
import com.sample.cloud.serviceapi.api.Student;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosTestController {
    @Reference
    private IStudentService studentService;
    @RequestMapping("student/{idNo}")
    public Student oneNacosConfig(@PathVariable("idNo") String idNo) {
        Student student = studentService.queryByIdno(idNo);
        return student;
    }
}
