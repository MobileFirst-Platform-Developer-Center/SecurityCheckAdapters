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

import com.ibm.mfp.security.checks.base.CredentialsValidationSecurityCheckConfig;

import java.util.Properties;


public class PinCodeConfig extends CredentialsValidationSecurityCheckConfig {

    public String pinCode;

    public PinCodeConfig(Properties properties) {
        //Make sure to load the parent properties
        super(properties);

        //Load the pinCode property
        pinCode = getStringProperty("pinCode", properties, "1234");

        //Check that the PIN code is at least 4 characters long. Triggers an error.
        if(pinCode.length() < 4) {
            addMessage(errors,"pinCode","pinCode needs to be at least 4 characters");
        }

        //Check that the PIN code is numeric. Triggers warning.
        try {
            int i = Integer.parseInt(pinCode);
        }
        catch(NumberFormatException nfe) {
            addMessage(warnings,"pinCode","PIN code contains non-numeric characters");
        }
    }

}