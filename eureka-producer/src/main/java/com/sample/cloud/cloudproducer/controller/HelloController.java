package com.sample.cloud.cloudproducer.controller;

import com.sample.cloud.serviceapi.api.HelloRemoteApi;
import com.sample.cloud.serviceapi.api.Student;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController implements HelloRemoteApi {
    @Override
    public Student hello(String name) {
        Student student = new Student();
        student.setIdno("ST"+new Random().nextInt(9999999));
        student.setName(name);
        System.err.println(student.getIdno());
       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return student;
    }

    @Override
    public String queryname(String name) {
        return "query";
    }
}
