package com.mznalmstpa.springjpa.controller;

import com.mznalmstpa.springjpa.entity.CountryEntity;
import com.mznalmstpa.springjpa.entity.DistrictEntity;
import com.mznalmstpa.springjpa.entity.ProvinceEntity;
import com.mznalmstpa.springjpa.entity.SubDistrictEntity;
import com.mznalmstpa.springjpa.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class ApiCountryController {

    private final CountryService countryService;

    @GetMapping()
    public List<CountryEntity> getCountry(){
        return this.countryService.getCountry();
    }

    @GetMapping("/province/{id}")
    public List<ProvinceEntity> getProvince(@PathVariable("id") Long id){
        return this.countryService.getProvince(id);
    }

    @GetMapping("district/{id}")
    public List<DistrictEntity> getDistrict(@PathVariable("id")Long id){
        return this.countryService.getDistrict(id);
    }

    @GetMapping("/sub-district/{id}")
    public List<SubDistrictEntity> getSubDistrict(@PathVariable("id")Long id){
        return this.countryService.getSubDistrict(id);
    }
}
