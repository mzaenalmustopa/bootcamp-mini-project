package com.app.sikolam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", length = 32)
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_roles_menu",
            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "MENU_ID", referencedColumnName = "ID"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ROLE_ID", "MENU_ID" })
    )
    private List<MenuEntity> menus = new ArrayList<>();

    public RoleEntity(String name){
        this.name = name;
    }

    public RoleEntity(String name, List<MenuEntity> menus) {
        this.name = name;
        for(MenuEntity item: menus){
            this.addMenu(item);
        }
    }

    public void addMenu(MenuEntity item){
        this.menus.add(item);
        item.getRoles().add(this);
    }
}
