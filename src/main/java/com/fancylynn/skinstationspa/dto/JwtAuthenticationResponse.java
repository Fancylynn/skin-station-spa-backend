package com.fancylynn.skinstationspa.dto;

import java.io.Serializable;

/**
 * Created by Lynn on 2018/4/2.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
