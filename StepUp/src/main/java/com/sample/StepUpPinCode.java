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
import com.ibm.mfp.security.checks.base.CredentialsValidationSecurityCheckConfig;
import com.ibm.mfp.server.registration.external.model.ClientData;
import com.ibm.mfp.server.registration.external.model.PersistentAttributes;
import com.ibm.mfp.server.security.external.checks.AuthorizationResponse;
import com.ibm.mfp.server.security.external.checks.SecurityCheckConfiguration;
import com.ibm.mfp.server.security.external.checks.SecurityCheckReference;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class StepUpPinCode extends CredentialsValidationSecurityCheck {

    private transient String errorMsg = null;

    @SecurityCheckReference
    private transient StepUpUserLogin userLogin;

    @Override
    public void authorize(Set<String> scope, Map<String, Object> credentials, HttpServletRequest request, AuthorizationResponse response) {
        if(userLogin.getState().equals(STATE_SUCCESS)){
            super.authorize(scope, credentials, request, response);
        }
    }

    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        PersistentAttributes attributes = registrationContext.getRegisteredProtectedAttributes();

        if(credentials!=null && credentials.containsKey("pinCode")){
            String pinCode = credentials.get("pinCode").toString();

            if(pinCode.equals(attributes.get("pinCode"))){
                errorMsg = null;
                return true;
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
