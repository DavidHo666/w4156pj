package com.insomnia_studio.w4156pj.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Define client Model.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
  private UUID clientId;
  private String clientName;

  public Client(String clientName) {
    this.clientName = clientName;
  }
}
