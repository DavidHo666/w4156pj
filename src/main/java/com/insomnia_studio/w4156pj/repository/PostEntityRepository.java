package com.insomnia_studio.w4156pj.repository;

import com.insomnia_studio.w4156pj.entity.PostEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*** Define PostEntityRepository.*/
@Repository
public interface PostEntityRepository extends JpaRepository<PostEntity, UUID> {
  PostEntity findByPostId(UUID id);

  Integer deletePostEntityByPostId(UUID id);

  boolean existsByPostId(UUID id);


}
