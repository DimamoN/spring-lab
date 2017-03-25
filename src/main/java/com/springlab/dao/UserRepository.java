package com.springlab.dao;

import com.springlab.entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserRepository {

    public User getUser(String username){
        return new User("test");
    }

    public List<User> getUsers(){
        return Arrays.asList(new User("test1"), new User("test2"));
    }

    public User createUser(User user){
        return user;
    }
}
