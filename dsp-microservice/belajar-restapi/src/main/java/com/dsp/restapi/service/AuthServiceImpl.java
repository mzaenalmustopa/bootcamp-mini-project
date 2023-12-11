package com.dsp.restapi.service;

import com.dsp.restapi.model.entity.UserEntity;
import com.dsp.restapi.model.request.LoginUserRequest;
import com.dsp.restapi.model.response.TokenResponse;
import com.dsp.restapi.repository.UserRepository;
import com.dsp.restapi.security.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        UserEntity user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpired(next30Days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpired())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    private BigInteger next30Days() {
        return BigInteger.valueOf(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 30));
    }

    @Transactional
    public void logout(UserEntity user) {
        user.setToken(null);
        user.setTokenExpired(null);

        userRepository.save(user);
    }
}
