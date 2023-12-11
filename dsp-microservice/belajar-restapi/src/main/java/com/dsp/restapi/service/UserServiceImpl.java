package com.dsp.restapi.service;

import com.dsp.restapi.exception.CommonApiException;
import com.dsp.restapi.model.entity.UserEntity;
import com.dsp.restapi.model.request.RegisterRequest;
import com.dsp.restapi.model.request.UpdateUserRequest;
import com.dsp.restapi.model.response.Response;
import com.dsp.restapi.model.response.UserResponse;
import com.dsp.restapi.repository.UserRepository;
import com.dsp.restapi.security.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Response> get() {
        List<UserEntity> result = repository.findAll();
        if(result.isEmpty()){
            return Optional.of(new Response(200,"Success", Collections.emptyList()));
        }

        List<UserResponse> responses = result.stream()
                .map(this::get)
                .toList();

        return Optional.of(new Response(200,"Success", responses));
    }

    @Override
    public Optional<Response> register(RegisterRequest request) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(request, entity);
        // encrypt password
        entity.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        entity.setId(UUID.randomUUID().toString());

        try{
            this.repository.save(entity);
            Response result = new Response(200,"Success", entity);
            return Optional.of(result);
        }catch (Exception e){
            throw new CommonApiException("Save user is failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserResponse get(UserEntity user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getEmail())
                .id(user.getId())
                .build();
    }

    @Transactional
    public UserResponse update(UserEntity user, UpdateUserRequest request) {

        log.info("REQUEST : {}", request);

        if (Objects.nonNull(request.getName())) {
            user.setEmail(request.getName());
        }

        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        repository.save(user);

        log.info("USER : {}", user.getEmail());

        return UserResponse.builder()
                .name(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
