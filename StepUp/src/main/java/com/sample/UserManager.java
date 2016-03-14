package com.sample;

import java.util.HashMap;
import com.sample.User;

public class UserManager {
    public HashMap users;

    public UserManager(){
        users = new HashMap();
        users.put("John", new User("John", "John Doe", "john123", "1234"));
        users.put("Tommy", new User("Tommy", "Tommy Atkins", "tommy234", "2345"));
        users.put("Fred", new User("Fred", "Fred Bloggs", "fred123", "3456"));
        users.put("Ola", new User("Ola", "Ola Nordmann", "ola123", "4567"));
    }

    public User getUser(String username){
        return (User) users.get(username);
    }
}
