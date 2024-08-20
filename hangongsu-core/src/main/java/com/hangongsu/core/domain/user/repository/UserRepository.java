package com.hangongsu.core.domain.user.repository;

import com.hangongsu.core.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);

}
