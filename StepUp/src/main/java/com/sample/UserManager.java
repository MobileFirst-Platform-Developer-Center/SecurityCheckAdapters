package com.sample;

import java.util.HashMap;
import com.sample.User;

public class UserManager {
    public HashMap users;

    public UserManager(){
        users = new HashMap();
        users.put("aaa", new User("aaa", "aaa aaa", "abcd", "1234"));
        users.put("bbb", new User("bbb", "bbb bbb", "bcde", "2345"));
        users.put("ccc", new User("ccc", "ccc ccc", "cdef", "1234"));
    }

    public User getUser(String username){
        return (User) users.get(username);
    }
}
