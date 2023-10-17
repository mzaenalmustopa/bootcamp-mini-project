package com.bootcamp.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupModel {
    private String id;
    private String groups;
    private String code;
    private String name;
    private String position;
    private Boolean active;

}
