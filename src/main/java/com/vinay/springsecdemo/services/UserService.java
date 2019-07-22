package com.vinay.springsecdemo.services;

import com.vinay.springsecdemo.models.Token;
import com.vinay.springsecdemo.models.User;

public interface UserService extends CrudServices<User,Long> {

    Token loginUser(User user);
}
