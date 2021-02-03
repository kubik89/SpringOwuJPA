package com.vb.less.demo.dao;

import com.vb.less.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserDao extends JpaRepository<User, Integer> {
// оскільки цей метод написаний по канону Spring, то він поверне кверю типу
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

// @Query("select u from User u where u.username like :username")
    User findByUsername(String username);
}
