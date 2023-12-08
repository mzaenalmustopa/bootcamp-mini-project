package com.app.sikolam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupModel {
    private Long id;
    private String group;
    private String code;
    private String name;
    private Integer position;
}
