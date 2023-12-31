package com.bootcamp.service;

import com.bootcamp.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    public Long getCount();
    public List<RoleEntity> get();
    public List<RoleEntity> getByNames(List<String> names);
    public RoleEntity getByName(String name);
    public RoleEntity getById(String id);
    public RoleEntity save(RoleEntity data);
    public RoleEntity save(List<RoleEntity> data);
    public RoleEntity update(RoleEntity data , String id);
    public RoleEntity delete(String id);

}
