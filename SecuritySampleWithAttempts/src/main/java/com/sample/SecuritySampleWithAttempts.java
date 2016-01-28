package com.sample;

import com.ibm.mfp.server.security.external.checks.impl.SecurityCheckWithAttempts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nathanh on 25/01/2016.
 */
public class SecuritySampleWithAttempts extends SecurityCheckWithAttempts{

    private transient String errorMsg = null;
    public static final String USER_FIELD = "user";
    public static final String PASSWORD_FIELD = "password";

    /**
     * Validate the credentials
     *
     * @param credentials credentials object, not null
     * @return true if the credentials are valid, false otherwise
     */
    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        if(credentials.containsKey(USER_FIELD) && credentials.containsKey(PASSWORD_FIELD)){
            String username = credentials.get(USER_FIELD).toString();
            String password = credentials.get(PASSWORD_FIELD).toString();

            //For our example, credentials are valid if username == password
            if(username.equals(password)){
                return true;
            }
            else {
                errorMsg = "username and password need to be identical";
            }

        }
        else{
            errorMsg = "username and password were not provided";
        }

        //In any other case, credentials are not valid
        return false;
    }

    /**
     * Create the challenge that should be sent to the client
     *
     * @return the challenge object, Map
     */
    @Override
    protected Map<String, Object> createChallenge() {
        HashMap challenge = new HashMap();
        challenge.put("errorMsg",errorMsg);
        challenge.put("remainingAttempts",remainingAttempts);
        return challenge;

    }
}
