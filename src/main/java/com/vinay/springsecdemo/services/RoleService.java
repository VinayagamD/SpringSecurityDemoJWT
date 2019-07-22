package com.vinay.springsecdemo.services;

import com.vinay.springsecdemo.models.Role;

public interface RoleService extends CrudServices<Role,Long> {
    Role findByRoleName(String name);
}
