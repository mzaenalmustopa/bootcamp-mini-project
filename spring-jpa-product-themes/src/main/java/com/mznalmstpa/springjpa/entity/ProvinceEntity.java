package com.mznalmstpa.springjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_province")
public class ProvinceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COUNTRY_ID", insertable = false, updatable = false)
    private Long countryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private CountryEntity country;

    @Column(name = "COUNTRY_CODE", length = 20, nullable = false)
    private String code;

    @Column(name = "COUNTRY_NAME", length = 128, nullable = false)
    private String name;

    public ProvinceEntity(CountryEntity country, String code, String name) {
        this.country = country;
        this.code = code;
        this.name = name;
    }
}
