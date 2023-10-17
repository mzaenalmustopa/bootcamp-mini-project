package com.bootcamp.service;

import com.bootcamp.entity.UserEntity;
import com.bootcamp.model.UserModel;

import java.util.List;

public interface UserService {
    public Long getCount();
    public List<UserEntity> get();
    public List<UserModel> getDto();
    public List<UserModel> getDtoByKeyword(String keyword);
    public UserEntity getByUsername(String name);
    public UserEntity getById(String id);
    public UserEntity save(UserEntity data);
    public UserEntity save(UserModel data);
    public UserEntity update(UserEntity data, String id);
    public UserEntity update (UserEntity data);
    public UserEntity update(UserModel data);
    public UserEntity delete(String id);
}
