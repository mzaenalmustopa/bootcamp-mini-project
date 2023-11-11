package com.mznalmstpa.springjpa.serviceImpl;

import com.mznalmstpa.springjpa.entity.CountryEntity;
import com.mznalmstpa.springjpa.entity.DistrictEntity;
import com.mznalmstpa.springjpa.entity.ProvinceEntity;
import com.mznalmstpa.springjpa.entity.SubDistrictEntity;
import com.mznalmstpa.springjpa.repository.CountryRepo;
import com.mznalmstpa.springjpa.repository.DistrictRepo;
import com.mznalmstpa.springjpa.repository.ProvinceRepo;
import com.mznalmstpa.springjpa.repository.SubDistrictRepo;
import com.mznalmstpa.springjpa.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;
    private final ProvinceRepo provinceRepo;
    private final DistrictRepo districtRepo;
    private final SubDistrictRepo subDistrictRepo;

    @Override
    public List<CountryEntity> getCountry() {
        return this.countryRepo.findAll();
    }

    @Override
    public List<ProvinceEntity> getProvince(Long countryId) {
        if(countryId == 0L){
            return Collections.emptyList();
        }

        return this.provinceRepo.findByCountryId(countryId);
    }

    @Override
    public List<DistrictEntity> getDistrict(Long provinceId) {
        if(provinceId == 0L){
            return Collections.emptyList();
        }
        return this.districtRepo.findByProvinceId(provinceId);
    }

    @Override
    public List<SubDistrictEntity> getSubDistrict(Long districtId) {
        if(districtId == 0L){
            return Collections.emptyList();
        }
        return this.subDistrictRepo.findByDistrictId(districtId);
    }
}
