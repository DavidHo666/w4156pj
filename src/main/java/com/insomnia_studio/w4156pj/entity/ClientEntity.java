package com.insomnia_studio.w4156pj.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


/**
 * Define Client Entity.
 */
@Entity
@Data
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID clientId;

  private String clientName;

  @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<PostEntity> posts;

  @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<CommentEntity> comments;

  @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<UserEntity> users;

  public ClientEntity(UUID clientId, String clientName) {
    this.clientId = clientId;
    this.clientName = clientName;
  }
}
