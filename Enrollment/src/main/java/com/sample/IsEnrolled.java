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


import com.ibm.mfp.server.registration.external.model.PersistentAttributes;
import com.ibm.mfp.server.security.external.checks.AuthorizationResponse;
import com.ibm.mfp.server.security.external.checks.IntrospectionResponse;
import com.ibm.mfp.server.security.external.checks.SecurityCheckConfiguration;
import com.ibm.mfp.server.security.external.checks.SecurityCheckReference;
import com.ibm.mfp.server.security.external.checks.impl.ExternalizableSecurityCheck;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class IsEnrolled  extends ExternalizableSecurityCheck{
    private static final String SUCCESS_STATE = "success";

    @SecurityCheckReference
    private transient EnrollmentUserLogin userLogin;

    @Override
    public SecurityCheckConfiguration createConfiguration(Properties properties) {
        return new IsEnrolledConfig(properties);
    }

    @Override
    protected void initStateDurations(Map<String, Integer> durations) {
        durations.put (SUCCESS_STATE, ((IsEnrolledConfig) config).successStateExpirationSec);
    }

    public void authorize(Set<String> scope, Map<String, Object> credentials, HttpServletRequest request, AuthorizationResponse response) {
        PersistentAttributes attributes = registrationContext.getRegisteredProtectedAttributes();
        if (attributes.get("pinCode") != null){
            // Is there a user currently active?
            if (!userLogin.isLoggedIn()){
                // If not, set one here.
                authorizationContext.setActiveUser(userLogin.getRegisteredUser());
            }
            setState(SUCCESS_STATE);
            response.addSuccess(scope, getExpiresAt(), getName(), "user", userLogin.getRegisteredUser());
        } else  {
            setState(STATE_EXPIRED);
            Map <String, Object> failure = new HashMap<String, Object>();
            failure.put("failure", "User is not enrolled");
            response.addFailure(getName(), failure);
        }
    }

    public void introspect(Set<String> checkScope, IntrospectionResponse response) {
        if (getState().equals(SUCCESS_STATE)) {
            response.addIntrospectionData(getName(),checkScope,getExpiresAt(),null);
        }
    }
}
