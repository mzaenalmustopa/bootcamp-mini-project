package com.dsp.restapi.service;

import com.dsp.restapi.model.entity.UserEntity;
import com.dsp.restapi.model.request.LoginUserRequest;
import com.dsp.restapi.model.response.TokenResponse;

public interface AuthService {
    public TokenResponse login(LoginUserRequest request);
    public void logout(UserEntity user);
}
