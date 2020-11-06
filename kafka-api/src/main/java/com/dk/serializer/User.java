package com.dk.serializer;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.Serializable;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 16:03
 **/
public class User implements Serializable,Protobufable{

    private Long id;

    private String name;

    private Integer gender;

    private String phone;

    public User() {}

    public User(Long id, String name, Integer gender, String phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }


    // 解码（反序列化） Consumer中用到
    // 利用生成的 UserProto对象
    public User(byte[] bytes) {
        try {
            //将字节数组转换为UserProto.User对象
            UserProto.User user = UserProto.User.parseFrom(bytes);
            //UserProto.User对象转化为自己的User对象
            this.id = user.getId();
            this.name = user.getName();
            this.phone = user.getPhone();
            this.gender = user.getGender();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    // 编码
    // 在ProtobufSerializer中用到，生产者指定使用它序列化
    @Override
    public byte[] encode() {
        // 利用生成的 UserProto对象
        UserProto.User.Builder builder = UserProto.User.newBuilder();
        builder.setId(id);
        builder.setName(name);
        builder.setPhone(phone);
        builder.setGender(gender);
        return builder.build().toByteArray();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }
}

