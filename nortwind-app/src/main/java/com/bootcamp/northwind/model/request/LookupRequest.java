package com.bootcamp.northwind.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LookupRequest {
    private String id;
    private String groups;
    private String code;
    private String name;
    private Integer position;
    private Boolean active;
}
