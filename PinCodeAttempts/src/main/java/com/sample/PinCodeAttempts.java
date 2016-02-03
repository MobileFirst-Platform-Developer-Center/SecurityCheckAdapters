package com.sample;

import com.ibm.mfp.server.security.external.checks.impl.SecurityCheckWithAttempts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nathanh on 03/02/2016.
 */
public class PinCodeAttempts extends SecurityCheckWithAttempts {

    private transient String errorMsg = null;
    public static final String PINCODE_FIELD = "pin";
    public static final String VALID_PINCODE = "1234";

    /**
     * Validate the credentials
     *
     * @param credentials credentials object, not null
     * @return true if the credentials are valid, false otherwise
     */
    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        if(credentials.containsKey(PINCODE_FIELD)){
            String pinCode = credentials.get(PINCODE_FIELD).toString();

            //For our example, credentials are valid if username == password
            if(pinCode.equals(VALID_PINCODE)){
                return true;
            }
            else {
                errorMsg = "Pin code is not valid. Hint: " + VALID_PINCODE;
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
