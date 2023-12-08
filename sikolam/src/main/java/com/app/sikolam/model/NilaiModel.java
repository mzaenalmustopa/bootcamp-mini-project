package com.app.sikolam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NilaiModel {
    private Long id;
    private String code;
    private String name;
    private String category;
}
