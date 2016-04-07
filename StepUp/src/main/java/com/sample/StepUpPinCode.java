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

import com.ibm.mfp.security.checks.base.CredentialsValidationSecurityCheck;
import com.ibm.mfp.server.security.external.checks.AuthorizationResponse;
import com.ibm.mfp.server.security.external.checks.SecurityCheckReference;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StepUpPinCode extends CredentialsValidationSecurityCheck {

    private transient String errorMsg = null;
    public static final String PINCODE_FIELD = "pin";
    private static UserManager userManager = new UserManager();

    //Get a reference of the StepUpUserLogin security check
    @SecurityCheckReference
    private transient StepUpUserLogin userLogin;

    @Override
    public void authorize(Set<String> scope, Map<String, Object> credentials, HttpServletRequest request, AuthorizationResponse response) {
        if(userLogin.isLoggedIn()){
            //If StepUpUserLogin is successful, continue the normal processing of StepUpPinCode
            super.authorize(scope, credentials, request, response);
        }
    }

    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        //Get the correct PIN code from the database
        User user = userManager.getUser(userLogin.getUser().getId());

        if(credentials!=null && credentials.containsKey(PINCODE_FIELD)){
            String pinCode = credentials.get(PINCODE_FIELD).toString();

            if(pinCode.equals(user.getPinCode())){
                errorMsg = null;
                return true;
            }
            else{
                errorMsg = "Wrong credentials. Hint: " + user.getPinCode();
            }
        }
        return false;
    }

    @Override
    protected Map<String, Object> createChallenge() {
        Map challenge = new HashMap();
        challenge.put("errorMsg",errorMsg);
        return challenge;
    }

}
