package com.app.sikolam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_lookup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupEntity {
    @Id
    @Column(name = "lookup_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lookup_group", length = 64)
    private String group;

    @Column(name = "lookup_code", length = 64, unique = true)
    private String code;

    @Column(name = "lookup_name", length = 128)
    private String name;

    @Column(name = "position")
    private Integer position;

    public LookupEntity(String group, String code, String name, Integer position) {
        this.group = group;
        this.code = code;
        this.name = name;
        this.position = position;
    }
}
