package com.bootcamp.northwind.config;

import com.bootcamp.northwind.model.entity.CategoryEntity;
import com.bootcamp.northwind.model.entity.LookupEntity;
import com.bootcamp.northwind.model.entity.RoleEntity;
import com.bootcamp.northwind.model.entity.UserEntity;
import com.bootcamp.northwind.repository.CategoryRepo;
import com.bootcamp.northwind.repository.RoleRepo;
import com.bootcamp.northwind.repository.UserRepo;
import com.bootcamp.northwind.service.LookupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Constants;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInit implements CommandLineRunner {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;
    private final LookupService lookupService;
    private final CategoryRepo categoryRepo;

    @Override
    public void run(String... args) throws Exception {
        log.info("Database init is called...");
        // generate role
        initRole();
        // generate user
        initUser();
        //generate lookup
        initLookup();
        initCategory();
    }

    private void initRole() {
        if (roleRepo.count() > 0){
            return;
        }
        try {
            this.roleRepo.saveAll(List.of(
                    new RoleEntity(UUID.randomUUID().toString(),"ROLE_ADMIN"),
                    new RoleEntity(UUID.randomUUID().toString(),"ROLE_USER"),
                    new RoleEntity(UUID.randomUUID().toString(), "ROLE_SUPER_USER")
            ));
            log.info("Save role success...!");
        }catch (Exception e){
            log.error("Save role failed, error: {}", e.getMessage());
        }
    }

    private void initUser() {
        if(userRepo.count() > 0){
            return;
        }

        // admin
        RoleEntity adminRole = roleRepo.findByName("ROLE_ADMIN").orElse(null);
        if(adminRole != null){
            UserEntity admin = new UserEntity("Admin","User","admin@gmail.com", encoder.encode("P@ssW0rd32!"),List.of(adminRole));
            try {
                userRepo.save(admin);
                log.info("Create admin role success..!");
            }catch (Exception e){
                log.error("Create admin role failed, Error: {}", e.getMessage());
            }
        }

        // user
        RoleEntity userRole = roleRepo.findByName("ROLE_USER").orElse(null);
        if(userRole != null){
            UserEntity user = new UserEntity("User","Role","user@gmail.com", encoder.encode("P@ssW0rd32!"),List.of(userRole));
            try {
                userRepo.save(user);
                log.info("Create user role success..!");
            }catch (Exception e){
                log.error("Create user role failed, Error: {}", e.getMessage());
            }
        }

        // super user
        RoleEntity superUserRole = roleRepo.findByName("ROLE_SUPER_USER").orElse(null);
        if(superUserRole != null){
            UserEntity superUser = new UserEntity("Super","User","super.user@gmail.com", encoder.encode("P@ssW0rd32!"),List.of(superUserRole));
            try {
                userRepo.save(superUser);
                log.info("Create super user role success..!");
            }catch (Exception e){
                log.error("Create super user role failed, Error: {}", e.getMessage());
            }
        }
    }

    private void initLookup(){
        if (lookupService.getByGroup("CATEGORY").isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookupEntity("CATEGORY","ELEKTRONIK","Elektronik",1),
                    new LookupEntity("CATEGORY","FASHION","Fashion",2),
                    new LookupEntity("CATEGORY","AKSESORIS","Aksesoris",3),
                    new LookupEntity("CATEGORY","MAKANAN","Makanan",4),
                    new LookupEntity("CATEGORY","PERKAKAS","Perkakas",5)
            ));
        }
    }

    private void initCategory(){
        if (categoryRepo.count() > 0L){
            categoryRepo.saveAll(Arrays.asList(
                    new CategoryEntity("Makanan", "Enak Dan Lezat"),
                    new CategoryEntity("Minuman", "Segar Dan Manis"),
                    new CategoryEntity("Aksesoris", "Bagus Dan Keren"),
                    new CategoryEntity("Fashion", "Baru Dan Berkualitas"),
                    new CategoryEntity("Elektronik", "Harga terjangkau")
            ));
        }
    }
}
