package com.example.spring_demo.thread.threadBatch.entity;

import lombok.Data;

@Data
public class UserEntity {

    private String userName;

    private String userId;

    public UserEntity(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
