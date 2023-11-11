package com.mznalmstpa.springjpa.service;

import com.mznalmstpa.springjpa.entity.CountryEntity;
import com.mznalmstpa.springjpa.entity.DistrictEntity;
import com.mznalmstpa.springjpa.entity.ProvinceEntity;
import com.mznalmstpa.springjpa.entity.SubDistrictEntity;

import java.util.List;

public interface CountryService {
    List<CountryEntity> getCountry();
    List<ProvinceEntity> getProvince(Long CountryId);
    List<DistrictEntity> getDistrict(Long ProvinceId);
    List<SubDistrictEntity> getSubDistrict(Long districtId);
}
