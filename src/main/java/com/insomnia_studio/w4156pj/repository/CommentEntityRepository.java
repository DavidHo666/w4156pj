package com.insomnia_studio.w4156pj.repository;

import com.insomnia_studio.w4156pj.entity.CommentEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Define the Repository class for Comment.
 */
@Repository
public interface CommentEntityRepository extends JpaRepository<CommentEntity, UUID> {
  CommentEntity findByCommentId(UUID commentID);

  Integer deleteCommentEntityByCommentId(UUID id);
}
