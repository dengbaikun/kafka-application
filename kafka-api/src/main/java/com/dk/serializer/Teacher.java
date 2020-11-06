package com.dk.serializer;

import java.io.Serializable;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 17:38
 **/
public class Teacher implements Serializable {

    private String name;

    private Integer age;

    private String phone;

    public Teacher(String name, Integer age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
