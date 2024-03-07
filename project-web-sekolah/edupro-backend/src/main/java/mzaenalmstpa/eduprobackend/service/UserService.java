package mzaenalmstpa.eduprobackend.service;

import mzaenalmstpa.eduprobackend.model.request.ChangePasswordRequest;

import java.security.Principal;

public interface UserService {

    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
