package com.insomnia_studio.w4156pj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/*** Define PostEntity.*/
@Entity
@Table(name = "post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class PostEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID postId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  @JsonBackReference
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "clientId")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private ClientEntity client;

  @Lob
  private String title;
  private String content;

  @ElementCollection
  @CollectionTable(name = "post_tags")
  private Set<String> tags = new HashSet<>();

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)

  private Set<CommentEntity> comments = new HashSet<>();

  @Temporal(TemporalType.TIMESTAMP)
  private Date postCreatedTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Date postUpdatedTime;

  /*** Define PostMethod .*/

  public PostEntity(UUID postId, UserEntity user, ClientEntity client) {
    this.postId = postId;
    this.user = user;
    this.client = client;
  }

  @PrePersist
  protected void onCreate() {
    postCreatedTime = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    postUpdatedTime = new Date();
  }
}
