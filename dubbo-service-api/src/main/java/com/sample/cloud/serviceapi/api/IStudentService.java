package com.sample.cloud.serviceapi.api;

import java.util.List;

public interface IStudentService {
    Student queryByIdno(String idNo);
    List<Student> queryByGrade(int grade,int clazz);
}
