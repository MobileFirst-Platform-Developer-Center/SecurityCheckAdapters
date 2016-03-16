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
import com.ibm.mfp.server.security.external.checks.SecurityCheckConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PinCodeAttempts extends CredentialsValidationSecurityCheck {

    private transient String errorMsg = null;
    public static final String PINCODE_FIELD = "pin";

    @Override
    public SecurityCheckConfiguration createConfiguration(Properties properties) {
        return new PinCodeConfig(properties);
    }
    @Override
    protected PinCodeConfig getConfiguration() {
        return (PinCodeConfig) super.getConfiguration();
    }

    /**
     * Validate the credentials
     *
     * @param credentials credentials object, not null
     * @return true if the credentials are valid, false otherwise
     */
    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        if(credentials!=null && credentials.containsKey(PINCODE_FIELD)){
            String pinCode = credentials.get(PINCODE_FIELD).toString();

            if(pinCode.equals(getConfiguration().pinCode)){
                errorMsg = null;
                return true;
            }
            else {
                errorMsg = "Pin code is not valid. Hint: " + getConfiguration().pinCode;
            }

        }
        else{
            errorMsg = "Pin code was not provided";
        }

        //In any other case, credentials are not valid
        return false;

    }

    /**
     * Create the challenge that should be sent to the client
     *
     * @return the challenge object
     */
    @Override
    protected Map<String, Object> createChallenge() {
        Map challenge = new HashMap();
        challenge.put("errorMsg",errorMsg);
        challenge.put("remainingAttempts",getRemainingAttempts());
        return challenge;
    }
}
