package yin.shu.netty.serialize;

import java.io.Serializable;

/**
 * @author
 * @create 2018-01-30 22:02
 **/
public class User implements Serializable{

    private String username;

    private int age;

    public User(String username,int age){
        this.username = username;
        this.age =age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "username:"+username+",age:"+age;
    }
}
