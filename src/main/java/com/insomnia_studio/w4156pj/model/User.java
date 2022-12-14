package com.insomnia_studio.w4156pj.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Define User Model.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private UUID userId;

  private UUID clientId;
  private String firstName;
  private String lastName;
  private Set<String> followers;
  private Set<String> followedBy;
  private Date userCreatedTime;

  /**
   * Define User Model Constructor.
   */
  public User(UUID clientId, String firstName, String lastName) {
    this.clientId = clientId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User(UUID clientId) {
    this.clientId = clientId;
  }
}
