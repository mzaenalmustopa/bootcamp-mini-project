package com.bootcamp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_tab")
public class UserEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "username", length = 64)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_tab", joinColumns = @JoinColumn(name = "user_id",
    referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id",
    referencedColumnName = "id"))
    private Set<RoleEntity> roles = new HashSet<>();

}
