package com.sample;

import com.ibm.mfp.server.security.external.checks.impl.SecurityCheckWithAttemptsConfig;

import java.util.Properties;

/**
 * Created by nathanh on 03/02/2016.
 */
public class PinCodeConfig extends SecurityCheckWithAttemptsConfig {

    public String pinCode;

    public PinCodeConfig(Properties properties) {
        super(properties);
        pinCode = getStringProperty("pinCode", properties, "1234");
    }

}
