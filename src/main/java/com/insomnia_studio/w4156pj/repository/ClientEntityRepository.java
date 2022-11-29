package com.insomnia_studio.w4156pj.repository;

import com.insomnia_studio.w4156pj.entity.ClientEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Define Client Entity Repository.
 */
@Repository
public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {
  boolean existsByClientId(UUID clientId);

  ClientEntity findByClientId(UUID clientId);
}
