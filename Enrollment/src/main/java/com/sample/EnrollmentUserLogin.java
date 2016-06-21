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

import com.ibm.mfp.security.checks.base.UserAuthenticationSecurityCheck;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;

import java.util.HashMap;
import java.util.Map;

public class EnrollmentUserLogin extends UserAuthenticationSecurityCheck{
    private String displayName;
    private String errorMsg;

    protected AuthenticatedUser createUser() {
        return new AuthenticatedUser(displayName, displayName, this.getName());
    }

    protected boolean validateCredentials(Map<String, Object> credentials) {
        if(credentials!=null && credentials.containsKey("username") && credentials.containsKey("password")){
            if (credentials.get("username")!=null && credentials.get("password")!=null) {
                String username = credentials.get("username").toString();
                String password = credentials.get("password").toString();
                if (username.equals(password)) {
                    displayName = username;

                    errorMsg = null;
                    return true;
                } else {
                    errorMsg = "Wrong Credentials";
                }
            }
        }
        else{
            errorMsg = "Credentials not set properly";
        }
        return false;
    }

    public boolean isLoggedIn(){
        return getState().equals(STATE_SUCCESS);
    }

    public AuthenticatedUser getRegisteredUser() {
        return registrationContext.getRegisteredUser();
    }

    protected Map<String, Object> createChallenge() {
        Map<String, Object> challenge = new HashMap<String, Object>();
        challenge.put("errorMsg", errorMsg);
        challenge.put("remainingAttempts", getRemainingAttempts());
        return challenge;
    }

}
