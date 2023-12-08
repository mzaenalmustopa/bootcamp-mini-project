package com.bootcamp.northwind.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lookup_tab")
public class LookupEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "look_groups")
    private String groups;

    @Column(name = "look_code")
    private String code;

    @Column(name = "look_name")
    private String name;

    @Column(name = "look_position")
    private Integer position;

    @Column(name = "look_active")
    private Boolean active;

    public LookupEntity(String groups, String code, String name, Integer position) {
        this.id = UUID.randomUUID().toString();
        this.groups = groups;
        this.code = code;
        this.name = name;
        this.position = position;
        this.active = true;
    }
}
