package com.sample;

import com.ibm.mfp.security.checks.base.CredentialsValidationSecurityCheckConfig;

import java.util.Properties;

/**
 * Created by nathanh on 03/02/2016.
 */
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