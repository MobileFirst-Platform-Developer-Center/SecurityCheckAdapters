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
import com.ibm.mfp.server.registration.external.model.PersistentAttributes;
import com.ibm.mfp.server.security.external.checks.AuthorizationResponse;
import com.ibm.mfp.server.security.external.checks.SecurityCheckReference;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnrollmentPinCode extends CredentialsValidationSecurityCheck {
    private String errorMsg;

    @SecurityCheckReference
    private transient EnrollmentUserLogin userLogin;

    @Override
    public void authorize(Set<String> scope, Map<String, Object> credentials, HttpServletRequest request, AuthorizationResponse response) {
        PersistentAttributes attributes = registrationContext.getRegisteredProtectedAttributes();
        if (userLogin.isLoggedIn()){
            setState(STATE_SUCCESS);
            response.addSuccess(scope, userLogin.getExpiresAt(), getName());
        } else {
            super.authorize(scope, credentials, request, response);
            if (getState().equals(STATE_BLOCKED)){
                attributes.delete("pinCode");
            }
        }
    }

    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        PersistentAttributes attributes = registrationContext.getRegisteredProtectedAttributes();
        if(credentials!=null && credentials.containsKey("pin")){
            String pinCode = credentials.get("pin").toString();

            if(pinCode.equals(attributes.get("pinCode"))){
                errorMsg = null;
                return true;
            }
            else {
                errorMsg = "Pin code is not valid. Hint: " + attributes.get("pinCode");
            }
        }
        else{
            errorMsg = "Pin code was not provided";
        }
        //In any other case, credentials are not valid
        return false;
    }

    @Override
    protected Map<String, Object> createChallenge() {
        Map<String, Object> challenge = new HashMap<String, Object>();
        challenge.put("errorMsg", errorMsg);
        challenge.put("remainingAttempts", getRemainingAttempts());
        return challenge;
    }
}
