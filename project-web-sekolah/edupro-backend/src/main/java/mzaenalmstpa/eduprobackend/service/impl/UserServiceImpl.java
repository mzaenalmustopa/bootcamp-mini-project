package mzaenalmstpa.eduprobackend.service.impl;

import lombok.RequiredArgsConstructor;
import mzaenalmstpa.eduprobackend.model.entity.UserEntity;
import mzaenalmstpa.eduprobackend.model.request.ChangePasswordRequest;
import mzaenalmstpa.eduprobackend.repository.UserRepo;
import mzaenalmstpa.eduprobackend.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getConfirmationPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong Password");
        }

        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepo.save(user);
    }
}
