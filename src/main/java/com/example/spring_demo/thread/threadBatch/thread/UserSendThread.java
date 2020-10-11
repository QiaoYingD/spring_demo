package com.example.spring_demo.thread.threadBatch.thread;

import com.example.spring_demo.thread.threadBatch.entity.UserEntity;

import java.util.List;

public class UserSendThread implements Runnable {

    private List<UserEntity> userEntityList;

    public UserSendThread(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    @Override
    public void run() {
        for (UserEntity userEntity : userEntityList) {
            System.out.println(Thread.currentThread().getName() + "," + userEntity.toString());
        }
        System.out.println();
    }
}
