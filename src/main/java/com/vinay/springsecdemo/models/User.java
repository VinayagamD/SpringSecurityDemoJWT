package com.vinay.springsecdemo.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "users")
@Data
@ToString(exclude = "roles")
@EqualsAndHashCode(callSuper = true,exclude = "roles")
public class User extends BaseModel {

    @Column(unique = true)
    private String email;
    private String password;
    private String fullName;
    private boolean enabled;


    @ManyToMany(cascade = {MERGE, REMOVE},fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")})
    private Set<Role>  roles = new HashSet<>();

    public User userProfile(){
        this.password = null;
        return this;
    }

}
