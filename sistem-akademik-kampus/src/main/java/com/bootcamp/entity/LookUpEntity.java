package com.bootcamp.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lookup_tab")
public class LookUpEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "lookup_group", length = 64, nullable = false)
    private String groups;

    @Column(name = "lookup_code", length = 20, nullable = false)
    private String code;

    @Column(name = "lookup_name", length = 100, nullable = false)
    private String name;

    @Column(name = "lookup_position", nullable = false)
    private Integer position;

    @Column(name = "lookup_active", nullable = false)
    private Boolean active;

    public LookUpEntity(String groups, String code, String name, Integer position) {
        this.id = UUID.randomUUID().toString();
        this.groups = groups;
        this.code = code;
        this.name = name;
        this.position = position;
        this.active = true;
    }
}
