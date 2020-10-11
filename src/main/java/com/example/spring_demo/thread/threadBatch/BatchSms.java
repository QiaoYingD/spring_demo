package com.example.spring_demo.thread.threadBatch;

import com.example.spring_demo.thread.threadBatch.entity.UserEntity;
import com.example.spring_demo.thread.threadBatch.thread.UserSendThread;
import com.example.spring_demo.thread.threadBatch.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @methodDesc: 功能描述:(多线程分批发送消息)
 * 目前网站有10万个用户，现在网站需要做活动，给每一个用户发送一条祝福短信。为了提高数程序的效率，使用多线程技术分批发送据
 * 每开一个线程，都会占用CPU资源，服务器（电脑）配置 CPU 核数，小项目可以使用，大项目需要使用消息中间件mq来实现
 */
public class BatchSms {

    public static void main(String[] args) {
        //1.初始化数据
        List<UserEntity> userEntityList = initUser();
        //2.定义每个线程分批发送大小
        int userSendCount = 2;
        //3.计算每个线程需要分配发送的数据
        List<List<UserEntity>> lists = ListUtils.splitList(userEntityList, userSendCount);
        //4.分配发送,使用线程池发送
        //创建一个固定长度的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            UserSendThread userSendThread = new UserSendThread(lists.get(i));
            executorService.execute(userSendThread);
        }
        executorService.shutdown();

    }

    private static List<UserEntity> initUser() {
        List<UserEntity> userList = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            UserEntity userEntity = new UserEntity("userName:" + i, "userId:" + i);
            userList.add(userEntity);
        }
        return userList;
    }


}
