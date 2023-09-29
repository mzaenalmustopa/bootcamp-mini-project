package com.bootcamp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_tab")
public class RoleEntity {
    @Id
    @Column(name = "id")
    private String id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();

    private RoleEntity(String name){
        this.name = name;
    }

    @PrePersist
    protected void onCreated(){
        id= UUID.randomUUID().toString().replace("-","");
    }
}
