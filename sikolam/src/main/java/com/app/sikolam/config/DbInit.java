package com.app.sikolam.config;

import com.app.sikolam.constant.CommonConstant;
import com.app.sikolam.entity.*;
import com.app.sikolam.respository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {
    private final LookupRepo lookupRepo;
    private final NilaiRepo nilaiRepo;
    private final RoleRepo roleRepo;
    private final MenuRepo menuRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        initLookup();
        initNilai();
        initRole();
        initUser();
    }

    private void initLookup(){
        if(lookupRepo.findByGroup(CommonConstant.GROUP_NILAI).isEmpty()) {
            lookupRepo.saveAll(
                    Arrays.asList(
                            new LookupEntity(CommonConstant.GROUP_NILAI, "NILAI_IBADAH","Ibadah", 1),
                            new LookupEntity(CommonConstant.GROUP_NILAI, "NILAI_KHALIFAH","Khalifah", 2),
                            new LookupEntity(CommonConstant.GROUP_NILAI, "NILAI_DAKWAH","Dakwah", 3)
                    )
            );
        }

        if(lookupRepo.findByGroup(CommonConstant.GROUP_IBADAH).isEmpty()){
            lookupRepo.saveAll(
                    Arrays.asList(
                            new LookupEntity(CommonConstant.GROUP_IBADAH, "IBADAH_IMAN","Iman", 0),
                            new LookupEntity(CommonConstant.GROUP_IBADAH, "IBADAH_ISLAM","Islam", 1)
                    )
            );
        }

        if(lookupRepo.findByGroup(CommonConstant.GROUP_KHALIFAH).isEmpty()) {
            lookupRepo.saveAll(
                    Arrays.asList(
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_LOGIKA","Logika", 1),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_LITERASI","Literasi", 2),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_BAKAT","Bakat", 3),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_FISIK","Fisik", 4),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_SKILLSET","Skillset", 5),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_KONSERVASI_ALAM","Konservasi Alam", 6),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_LEADERSHIP","Leadership", 7),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_ENTERPRENEURSHIP","Enterpreneurship", 8),
                            new LookupEntity(CommonConstant.GROUP_KHALIFAH, "KHALIFAH_TECHNOLOGY","Technology", 9)
                    )
            );
        }

        if(lookupRepo.findByGroup(CommonConstant.GROUP_DAKWAH).isEmpty()) {
            lookupRepo.saveAll(
                    Arrays.asList(
                            new LookupEntity(CommonConstant.GROUP_DAKWAH, "DAKWAH_KEBAIKAN","Kebaikan", 1),
                            new LookupEntity(CommonConstant.GROUP_DAKWAH, "DAKWAH_KEBENARAN","Kebenaran", 2)
                    )
            );
        }
    }

    private void initNilai(){
        if(nilaiRepo.findByCategory("IBADAH_IMAN").isEmpty()){
            nilaiRepo.saveAll( Arrays.asList(
                    new NilaiEntity( "IMAN_KHAUF","Khauf", "IBADAH_IMAN",1),
                    new NilaiEntity( "IMAN_ROJA","Roja", "IBADAH_IMAN",2),
                    new NilaiEntity( "IMAN_TAWAKKAL","Tawakkal", "IBADAH_IMAN",3),
                    new NilaiEntity( "IMAN_MUROQOBATULLAH","Muroqobatullah", "IBADAH_IMAN",4),
                    new NilaiEntity( "IMAN_YAKIN","Yakin", "IBADAH_IMAN",5),
                    new NilaiEntity( "IMAN_THOAT","Thoat", "IBADAH_IMAN",6),
                    new NilaiEntity( "IMAN_TAUBAT","Taubat", "IBADAH_IMAN",7),
                    new NilaiEntity( "IMAN_MUJAHADAH","Mujahadah", "IBADAH_IMAN",8),
                    new NilaiEntity( "IMAN_RIDHO","Ridho", "IBADAH_IMAN",9),
                    new NilaiEntity( "IMAN_TADZIM","Tadzim", "IBADAH_IMAN",10)
                    )
            );
        }
    }

    private void initRole(){
        if(roleRepo.count() > 0){
            return;
        }

        MenuEntity dashboard = menuRepo.save(new MenuEntity("DashboardMenu","Dashboard","/dashboard","","fas fa-tachometer-alt",1,"",null));
        MenuEntity master = menuRepo.save(new MenuEntity("MasterMenu","Master","/master","","fas fa-cog",2,"",null));
        List<MenuEntity> masterSub = Arrays.asList(
                new MenuEntity("MasterSub1","User","/master/user","","far fa-circle",1,"",master),
                new MenuEntity("MasterSub2","Role","/master/role","","far fa-circle",2,"",master),
                new MenuEntity("MasterSub3","Grade","/master/grade","","far fa-circle",3,"",master),
                new MenuEntity("MasterSub4","Factor","/master/factor","","far fa-circle",4,"",master),
                new MenuEntity("MasterSub5","Table","/master/lookup","","far fa-circle",5,"",master),
                new MenuEntity("MasterSub6","Reference","/master/reference","","far fa-circle",6,"",master)
        );
        this.menuRepo.saveAll(masterSub);

        MenuEntity customerMenu = this.menuRepo.save(new MenuEntity("CustomerMenu","Customer","/customer","","fas fa-users",3,"",null));
        List<MenuEntity> cusInquire = Arrays.asList(
                new MenuEntity("CustomerSubMenu1","Customer Data","/customer","","fas fa-tachometer-alt",1,"",customerMenu),
                new MenuEntity("CustomerSubMenu2","Customer Inquiry","/customer/inquiry","","fas fa-tachometer-alt",2,"",customerMenu),
                new MenuEntity("CustomerSubMenu3","Pefindo Individual","/pefindo/individual","","fas fa-tachometer-alt",3,"",customerMenu),
                new MenuEntity("CustomerSubMenu4","Pefindo Company","/pefindo/company","","fas fa-tachometer-alt",4,"",customerMenu)
        );
        this.menuRepo.saveAll(cusInquire);

        MenuEntity scoreMenu = this.menuRepo.save(new MenuEntity("ScoreMenu","Scoring","/scoring/business","","fas fa-tachometer-alt",4,"",null));
        List<MenuEntity> scoreMenuSub = Arrays.asList(
                new MenuEntity("ScoringMenuSub1","Business Score","/scoring/business/list","","far fa-circle",1,"",scoreMenu),
                new MenuEntity("ScoringMenuSub2","Retail Score","/scoring/retail/list","","far fa-circle",2,"",scoreMenu),
                new MenuEntity("ScoringMenuSub3","Upload Score","/scoring/upload","","far fa-circle",3,"",scoreMenu)
        );
        this.menuRepo.saveAll(scoreMenuSub);

        try {
            roleRepo.saveAll(List.of(
                    new RoleEntity("ROLE_ADMIN", List.of(dashboard, master, customerMenu)),
                    new RoleEntity("ROLE_USER", List.of(customerMenu)),
                    new RoleEntity("ROLE_SUPER_USER", List.of(dashboard, master, customerMenu))
            ));
            log.info("Save role success..!");
        }catch (Exception e){
            log.error("Save role failed, error: {}", e.getMessage());
        }
    }

    private void initUser(){
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
        if(adminRole != null){
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
}
