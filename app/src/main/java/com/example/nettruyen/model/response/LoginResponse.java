package com.example.nettruyen.model.response;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserResponse account;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserResponse getAccount() {
        return account;
    }

    public void setAccount(UserResponse account) {
        this.account = account;
    }
}
