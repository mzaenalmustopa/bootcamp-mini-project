package com.app.sikolam.security;

import com.app.sikolam.entity.MenuEntity;
import com.app.sikolam.entity.RoleEntity;
import com.app.sikolam.entity.UserEntity;
import com.app.sikolam.respository.UserRepo;
import groovy.util.logging.Slf4j;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Roni Purwanto
 * @since : 01/05/2021
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class MenuHelper {
    private final UserRepo userRepo;
    private static UserRepo staticUserService;

    public static Comparator<MenuEntity> comparator = Comparator.comparing(MenuEntity::getPosition);

    @PostConstruct
    public void init(){
        staticUserService = userRepo;
    }

    public static List<MenuEntity> menuList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<MenuEntity> menuList = new ArrayList<>();
        List<RoleEntity> roles;
        UserEntity userEntity = null;
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof User){
                userEntity = staticUserService.findByUsername(((User) principal).getUsername()).orElse(null);
            }
        }

        if(userEntity == null || userEntity.getRoles().isEmpty()){
            return Collections.emptyList();
        }

        roles = userEntity.getRoles();
        for(RoleEntity role: roles){
            for (MenuEntity menuTab: role.getMenus()){
                List<MenuEntity> check = menuList.stream().filter(m -> m.getId().equals(menuTab.getId())).collect(Collectors.toList());
                if(check.isEmpty()){
                    menuList.add(menuTab);
                }
            }
        }
        return menuList;
    }
}
