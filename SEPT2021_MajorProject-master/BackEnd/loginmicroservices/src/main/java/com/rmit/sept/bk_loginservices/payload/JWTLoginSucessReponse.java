package com.rmit.sept.bk_loginservices.payload;

import com.rmit.sept.bk_loginservices.utility.UserRole;

public class JWTLoginSucessReponse {
    private boolean success;
    private String token;
    private UserRole userRole;

    public JWTLoginSucessReponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}
