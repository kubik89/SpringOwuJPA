package com.vb.less.demo.service;

import com.vb.less.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

// свій інтерфейс UserService щоб простіше його передавати в UserDetailsService
// але щоб його передавати, слід перше створити юзера.
public interface IUserService extends UserDetailsService {
    String createUser(User user);
}
