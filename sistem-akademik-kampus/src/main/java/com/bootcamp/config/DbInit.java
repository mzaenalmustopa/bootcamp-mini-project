package com.bootcamp.config;

import com.bootcamp.entity.LookUpEntity;
import com.bootcamp.service.LookupService;
import com.bootcamp.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {

    private final LookupService lookupService;
    @Override
    public void run(String... args) throws Exception {

        initLookup();
    }

    private void initLookup(){
        if (lookupService.getByGroups("HARI").isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookUpEntity("HARI", "SENIN","Senin",1),
                    new LookUpEntity("HARI","SELASA","Selasa",2),
                    new LookUpEntity("HARI","RABU","Rabu",3),
                    new LookUpEntity("HARI","KAMIS","Kamis",4),
                    new LookUpEntity("HARI","JUMAT","Jumat",5),
                    new LookUpEntity("HARI","SABTU","Sabtu",6)
            ));
        }

        if (lookupService.getByGroups("SEMESTER").isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookUpEntity("SEMESTER","GANJIL","ganjil",1),
                    new LookUpEntity("SEMESTER","GENAP","genap",2)
            ));
        }

        if (lookupService.getByGroups("ONLINE").isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookUpEntity("ONLINE","ONLINE","Online",1),
                    new LookUpEntity("ONLINE","OFFLINE","Offline",2)
            ));
        }

        if (lookupService.getByGroups(Constants.GENDER).isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookUpEntity("GENDER","PRIA","Pria",1),
                    new LookUpEntity("GENDER","WANITA","Wanita",2)
            ));
        }

        if (lookupService.getByGroups(Constants.AGAMA).isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookUpEntity("AGAMA","ISLAM","Islam",1),
                    new LookUpEntity("AGAMA","KRISTEN","Kristen",2),
                    new LookUpEntity("AGAMA","KATHOLIK","Katholik",3),
                    new LookUpEntity("AGAMA","HINDU","Hindu",4),
                    new LookUpEntity("AGAMA","BUDHA","Budha",5),
                    new LookUpEntity("AGAMA","KONGHUCU","Konghucu",6),
                    new LookUpEntity("AGAMA","LAINNYA","Lainnya",7)
            ));
        }
    }
}
