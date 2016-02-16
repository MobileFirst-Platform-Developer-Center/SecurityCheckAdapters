package com.sample;

import com.ibm.mfp.security.checks.base.CredentialsValidationSecurityCheck;
import com.ibm.mfp.server.security.external.checks.SecurityCheckConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by nathanh on 03/02/2016.
 */
public class PinCodeAttempts extends CredentialsValidationSecurityCheck {

    private transient String errorMsg = null;
    public static final String PINCODE_FIELD = "pin";

    @Override
    public SecurityCheckConfiguration createConfiguration(Properties properties) {
        return new PinCodeConfig(properties);
    }
    @Override
    protected PinCodeConfig getConfig() {
        return (PinCodeConfig) super.getConfig();
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

            if(pinCode.equals(getConfig().pinCode)){
                return true;
            }
            else {
                errorMsg = "Pin code is not valid. Hint: " + getConfig().pinCode;
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
        HashMap challenge = new HashMap();
        challenge.put("errorMsg",errorMsg);
        challenge.put("remainingAttempts",remainingAttempts);
        return challenge;
    }
}
