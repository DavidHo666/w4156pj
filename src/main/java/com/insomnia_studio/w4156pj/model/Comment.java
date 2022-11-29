package com.insomnia_studio.w4156pj.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  private UUID commentId;

  private UUID userId;

  private UUID postId;

  private UUID clientId;

  private Integer LikesNum;

  private Integer dislikesNum;

  private String content;

  private Date commentCreatedTime;

  private Date commentUpdatedTime;

  public Comment(UUID clientId, UUID userId, UUID postId, Integer likesNum, Integer dislikesNum, String content) {
    this.clientId = clientId;
    this.userId = userId;
    this.postId = postId;
    this.LikesNum = likesNum;
    this.dislikesNum = dislikesNum;
    this.content = content;
  }

  public Comment(UUID clientId) {
    this.clientId = clientId;
  }

}
