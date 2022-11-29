package com.insomnia_studio.w4156pj.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * Define Comment Entity.
 */

@Entity
@Table(name = "comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class CommentEntity implements Serializable {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID commentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "clientId")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private ClientEntity client;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postId")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private PostEntity post;

  @ManyToOne(fetch = FetchType.LAZY)
  private CommentEntity parentComment;

  @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<CommentEntity> childComments = new HashSet<>();

  @Temporal(TemporalType.TIMESTAMP)
  private Date commentCreatedTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Date commentUpdatedTime;
  private Integer LikesNum;
  private Integer dislikesNum;
  @Lob
  private String content;

  /**
   * Define Custom Constructors.
   */
  public CommentEntity(UUID commentId, UserEntity user, ClientEntity client, PostEntity post) {
    this.commentId = commentId;
    this.user = user;
    this.client = client;
    this.post = post;
  }

  @PrePersist
  protected void onCreate() {
    commentCreatedTime = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    commentUpdatedTime = new Date();
  }
}
