package com.example.spring_demo.zookeeper.watcher;

import org.apache.zookeeper.KeeperException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZkClientWatcherTest {


    @GetMapping("/watcherCreateNode")
    public String watcherCreateNode(String path, String data) throws KeeperException, InterruptedException {
        ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
        zkClientWatcher.createNode(path, data);
        zkClientWatcher.close();
        return path;
    }

    @GetMapping("/watcherGetNode")
    public String watcherGetNode(String path) throws KeeperException, InterruptedException {
        ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
        String data = zkClientWatcher.getData(path);
        zkClientWatcher.close();
        return data;
    }

}
