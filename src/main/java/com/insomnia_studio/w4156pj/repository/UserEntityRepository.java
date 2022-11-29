package com.insomnia_studio.w4156pj.repository;

import com.insomnia_studio.w4156pj.entity.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
  UserEntity findByUserId(UUID userId);

  Integer deleteUserEntityByUserId(UUID userId);


}
