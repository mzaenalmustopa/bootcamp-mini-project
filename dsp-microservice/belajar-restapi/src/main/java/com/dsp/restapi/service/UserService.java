package com.dsp.restapi.service;

import com.dsp.restapi.model.entity.UserEntity;
import com.dsp.restapi.model.request.RegisterRequest;
import com.dsp.restapi.model.request.UpdateUserRequest;
import com.dsp.restapi.model.response.Response;
import com.dsp.restapi.model.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Response> get();
    Optional<Response> register(RegisterRequest request);
    public UserResponse get(UserEntity user);
    public UserResponse update(UserEntity user, UpdateUserRequest request);
}
