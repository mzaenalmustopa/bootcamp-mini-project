package mzaenalmstpa.eduprobackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mzaenalmstpa.eduprobackend.config.JwtService;
import mzaenalmstpa.eduprobackend.model.entity.RoleEntity;
import mzaenalmstpa.eduprobackend.model.entity.TokenEntity;
import mzaenalmstpa.eduprobackend.model.entity.TokenType;
import mzaenalmstpa.eduprobackend.model.entity.UserEntity;
import mzaenalmstpa.eduprobackend.model.request.AuthenticationRequest;
import mzaenalmstpa.eduprobackend.model.request.RegisterRequest;
import mzaenalmstpa.eduprobackend.model.response.AuthenticationResponse;
import mzaenalmstpa.eduprobackend.repository.RoleRepo;
import mzaenalmstpa.eduprobackend.repository.TokenRepo;
import mzaenalmstpa.eduprobackend.repository.UserRepo;
import mzaenalmstpa.eduprobackend.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final TokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        List<RoleEntity> roles = roleRepo.findByNameIn(request.getRoles());
        if (roles.isEmpty()){
            roles = this.saveRoles(request.getRoles());
        }

        user.setRoles(roles);

        var savedUser = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var expiredAt = jwtService.expiredAt(jwtToken);

        savedUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var expiredAt = jwtService.expiredAt(jwtToken);

        revokeAllUserTokens(user);
        savedUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    };

    private void savedUserToken(UserEntity user , String jwtToken){
        var token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null){
            var user = this.userRepo.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                savedUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public List<RoleEntity> saveRoles(List<String> roles){
        if (roles.isEmpty()){
            return Collections.emptyList();
        }

        List<RoleEntity> roleEntities = new ArrayList<>();
        for (String role: roles){
            roleEntities.add(new RoleEntity(role));
        }

        try {
            roleRepo.saveAll(roleEntities);
            return roleEntities;
        } catch (Exception e){
            return Collections.emptyList();
        }
    }

    private void revokeAllUserTokens(UserEntity user){
        var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepo.saveAll(validUserTokens);
    }
}
