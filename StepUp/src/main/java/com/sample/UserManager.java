/**
 * Copyright 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sample;

import java.util.HashMap;

/**
 * This class hardcodes a list of valid users.
 * Replace this with your own implementation, such as a DataBase layer.
 */
public class UserManager {
    public HashMap users;

    public UserManager(){
        users = new HashMap();

        //The User constructors sets User(displayName, id, password, pinCode)
        users.put("john", new User("John Doe", "john", "john123", "1234"));
        users.put("tommy", new User("Tommy Atkins", "tommy", "tommy234", "2345"));
        users.put("fred", new User("Fred Bloggs", "fred", "fred123", "3456"));
        users.put("ola", new User("Ola Nordmann", "ola", "ola123", "4567"));
    }

    public User getUser(String id){
        return (User) users.get(id);
    }
}
