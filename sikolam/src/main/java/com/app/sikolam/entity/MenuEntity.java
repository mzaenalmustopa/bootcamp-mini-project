package com.app.sikolam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menu_name", length = 64, nullable = false, unique = true)
    private String name;

    @Column(name = "menu_title", length = 100, nullable = false)
    private String title;

    @Column(name = "menu_url", length = 200, nullable = false)
    private String url;

    @Column(name = "menu_desc", length = 200)
    private String description;

    @Column(name = "menu_icon", length = 100)
    private String icon;

    @Column(name = "menu_param", length = 100)
    private String params;

    @Column(name="position")
    private Integer position;

    @Column(name = "parent_id", insertable = false, updatable = false)
    private Long parentId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MenuEntity parentMenu;

    @JsonIgnore
    @OneToMany(mappedBy = "parentMenu")
    private List<MenuEntity> subMenu = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "menus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoleEntity> roles = new ArrayList<>();

    public MenuEntity(String name, String title, String url, String description, String icon, Integer position, String params, MenuEntity parentMenu) {
        this.name = name;
        this.title = title;
        this.url = url;
        this.description = description;
        this.icon = icon;
        this.params = params;
        this.position = position;
        this.parentMenu = parentMenu;
    }

    public void addSubMenu(MenuEntity menuEntity){
        this.subMenu.add(menuEntity);
        menuEntity.setParentMenu(this);
    }

    public void removeSubMenu(MenuEntity menuEntity){
        this.subMenu.remove(menuEntity);
        menuEntity.setParentMenu(null);
    }
}
