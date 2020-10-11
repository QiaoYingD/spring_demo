package com.example.spring_demo.iocdicheng;

public class UserInfo {

    private String userName;

    private String sex;


    public UserInfo() {
        System.out.println("使用反射技术，执行无参构造函数！");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
