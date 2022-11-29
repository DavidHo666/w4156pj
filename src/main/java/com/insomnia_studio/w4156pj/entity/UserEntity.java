package com.insomnia_studio.w4156pj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * Define User Entity.
 */
@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID userId;

  private String firstName;

  private String lastName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "clientId")
  private ClientEntity client;

  @ElementCollection
  @CollectionTable(name = "user_followers")
  private Set<UUID> followers = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "user_followedBy")
  private Set<UUID> followedBy = new HashSet<>();

  @Temporal(TemporalType.TIMESTAMP)
  private Date userCreatedTime;
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<PostEntity> posts;
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<CommentEntity> comments;

  /**
   * Define User Entity Constructor.
   */
  public UserEntity(UUID userId, String firstName, String lastName, ClientEntity client) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.client = client;
  }


  @PrePersist
  protected void onCreate() {
    userCreatedTime = new Date();
  }

}
