package com.vinay.springsecdemo.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@ToString(exclude = "users")
@EqualsAndHashCode(callSuper = true,exclude = "users")
public class Role extends BaseModel {

    @Column(unique = true)
    private String role;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

}
