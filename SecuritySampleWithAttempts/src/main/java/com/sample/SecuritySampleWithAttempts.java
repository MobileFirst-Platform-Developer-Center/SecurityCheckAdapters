package com.sample;

import com.ibm.mfp.server.security.external.checks.impl.SecurityCheckWithAttempts;

import java.util.Map;

/**
 * Created by nathanh on 25/01/2016.
 */
public class SecuritySampleWithAttempts extends SecurityCheckWithAttempts{
    /**
     * Validate the credentials
     *
     * @param credentials credentials object, not null
     * @return true if the credentials are valid, false otherwise
     */
    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        if(credentials.containsKey("username") && credentials.containsKey("password")){
            String username = credentials.get("username").toString();
            String password = credentials.get("password").toString();

            //For our example, credentials are valid if username == password
            if(username.equals(password)){
                return true;
            }

        }

        //In any other case, credentials are not valid
        return false;
    }

    /**
     * Create the challenge that should be sent to the client
     *
     * @return the challenge object, may be a simple type (primitive or String), or a POJO/Map
     */
    @Override
    protected Map<String, Object> createChallenge() {
        return null;
    }
}
