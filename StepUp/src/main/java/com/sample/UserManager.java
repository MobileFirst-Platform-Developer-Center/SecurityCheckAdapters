package com.sample;

import java.util.HashMap;
import com.sample.User;

public class UserManager {
    public HashMap users;

    public UserManager(){
        users = new HashMap();
        users.put("john", new User("john", "John Doe", "john123", "1234"));
        users.put("tommy", new User("tommy", "Tommy Atkins", "tommy234", "2345"));
        users.put("fred", new User("fred", "Fred Bloggs", "fred123", "3456"));
        users.put("ola", new User("ola", "Ola Nordmann", "ola123", "4567"));
    }

    public User getUser(String username){
        return (User) users.get(username);
    }
}
