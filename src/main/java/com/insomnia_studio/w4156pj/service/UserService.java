package com.insomnia_studio.w4156pj.service;

import com.insomnia_studio.w4156pj.model.User;
import java.util.UUID;

/**
 * Define User Service.
 */
public interface UserService {
  User addUser(User user) throws Exception;

  User getUser(UUID userId, User user) throws Exception;

  User updateUserById(UUID userId, User user) throws Exception;

  Boolean deleteUserById(UUID userId, User user) throws Exception;

}
