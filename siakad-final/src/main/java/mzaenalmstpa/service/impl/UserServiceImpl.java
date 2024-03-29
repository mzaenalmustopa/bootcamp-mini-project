package mzaenalmstpa.service.impl;

import lombok.extern.slf4j.Slf4j;
import mzaenalmstpa.entity.RoleEntity;
import mzaenalmstpa.entity.UserEntity;
import mzaenalmstpa.model.UserModel;
import mzaenalmstpa.repository.UserRepository;
import mzaenalmstpa.service.RoleService;
import mzaenalmstpa.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @Override
    public Long getCount() {
        return this.repository.count();
    }

    @Override
    public List<UserEntity> get() {
        List<UserEntity> result = this.repository.findAll();
        if (result == null)
            result = Collections.emptyList();
        log.info("Get User data: {}", result);
        return result;
    }

    @Override
    public List<UserModel> getDto() {
        List<UserEntity> result = this.repository.findAll();
        if (result.size() == 0) {
            log.info("Get User data empty");
            return Collections.emptyList();
        }else {
            log.info("Get User data result: {}", result);
            return result.stream().map(UserModel::new).collect(Collectors.toList());
        }
    }

    @Override
    public List<UserModel> getDtoByKeyword(String keyword) {
        List<UserEntity> result = this.repository.findByUsernameContaining(keyword);
        if (result.size() == 0){
            log.info("Get User data empty");
            return Collections.emptyList();
        }else {
            log.info("Get User data result: {}", result);
            return result.stream().map(UserModel::new).collect(Collectors.toList());
        }
    }

    @Override
    public UserEntity getByusername(String name) {
        UserEntity result = this.repository.findByUsername(name);
        if (result == null)
            result = new UserEntity();
        log.info("Get User by name: {}, data {}", name, result);
        return result;
    }

    @Override
    public UserEntity getById(String id) {
        UserEntity result = this.repository.findByIdFetchRoles(id);
        log.info("Get User data by id: {}, data {}", id, result);
        return result;
    }

    @Override
    public UserEntity save(UserEntity data) {
        try {
            this.repository.save(data);
            log.info("Save User data is success, data {}", data);
            return data;
        }catch (Exception e) {
            log.error("Save User error: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserEntity save(UserModel data) {
        if (data.getId() == null || data.getId().equals("")){
            UserEntity userEntity = new UserEntity(data, passwordEncoder);
            if (data.getRoles().size() > 0){
                List<RoleEntity> roleEntities = this.roleService.getByNames(data.getRoles());
                if (roleEntities.size() > 0){
                    userEntity.setRoles(new HashSet<>(roleEntities));
                }
            }
            if (!data.getRole().equals("") && data.getRole() != null){
                RoleEntity roleEntity = this.roleService.getByName(data.getRole());
                userEntity.setRoles(new HashSet<>(Arrays.asList(roleEntity)));
            }
            return this.save(userEntity);
        } else {
            return this.update(data);
        }
    }

    @Override
    public UserEntity update(UserEntity data, String id) {
        UserEntity result = this.repository.findById(id).orElse(null);
        if (result != null){
            try {
                BeanUtils.copyProperties(data, result);
                this.repository.save(result);
                log.info("Update User data is success, data: {}", result);
                return result;
            } catch (Exception e){
                log.error("Save User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        } else {
            log.info("Can not find User with id: {}", id);
            return null;
        }
    }

    @Override
    public UserEntity update(UserEntity data) {
        UserEntity result = this.repository.findById(data.getId()).orElse(null);
        if (result != null){
            try {
                BeanUtils.copyProperties(data, result);
                this.repository.save(result);
                log.info("Update User data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Update User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find User with id: {}", data.getId());
            return null;
        }
    }

    @Override
    public UserEntity update(UserModel data) {
        UserEntity result = this.repository.findById(data.getId()).orElse(null);
        if (result != null){
            try {
                BeanUtils.copyProperties(data, result);
                result.setPassword(passwordEncoder.encode(data.getPassword()));
                this.repository.save(result);
                log.info("Update User data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Update User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find User with id: {}", data.getId());
            return null;
        }
    }

    @Override
    public UserEntity delete(String id) {
        UserEntity result = this.repository.findByIdFetchRoles(id);
        if (result != null){
            try {
                if (result.getRoles().size() > 0){
                    for (RoleEntity roleEntity: result.getRoles()){
                        result.removeRole(roleEntity);
                    }
                }
                this.repository.delete(result);
                log.info("Delete User data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Save User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find User with id: {}", id);
            return null;
        }
    }
}
