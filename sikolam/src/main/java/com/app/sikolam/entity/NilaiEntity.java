package com.app.sikolam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_nilai")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NilaiEntity {
    @Id
    @Column(name = "nilai_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nilai_code", length = 64, unique = true)
    private String code;

    @Column(name = "nilai_name", length = 128)
    private String name;

    @Column(name = "category", length = 64)
    private String category;

    @Column(name = "position")
    private Integer positon;

    public NilaiEntity(String code, String name, String category, Integer positon) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.positon = positon;
    }
}
