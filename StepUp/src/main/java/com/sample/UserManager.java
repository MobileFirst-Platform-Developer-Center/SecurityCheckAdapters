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
