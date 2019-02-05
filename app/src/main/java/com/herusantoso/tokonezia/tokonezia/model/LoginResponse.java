package com.herusantoso.tokonezia.tokonezia.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("expires_in")
    private Long expiresIn;

    @SerializedName("scope")
    private String scope;

    @SerializedName("email")
    private String email;

    @SerializedName("jti")
    private String jti;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                ", email='" + email + '\'' +
                ", jti='" + jti + '\'' +
                '}';
    }
}
