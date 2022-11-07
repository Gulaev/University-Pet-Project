package com.gulaev.models;

import java.util.Objects;
import lombok.Data;

@Data
public class Audience implements Model {

  private Integer audienceId;
  private Integer audienceNumber;
  private Integer floor;
  private Integer numberOfSeats;
  private boolean interactiveWhiteboard;
  private boolean isEmpty;

  public Audience() {}

  public Audience(
      Integer audienceId,
      Integer audienceNumber,
      Integer floor,
      Integer numberOfSeats,
      boolean interactiveWhiteboard,
      boolean isEmpty) {
    this.audienceId = audienceId;
    this.audienceNumber = audienceNumber;
    this.floor = floor;
    this.numberOfSeats = numberOfSeats;
    this.interactiveWhiteboard = interactiveWhiteboard;
    this.isEmpty = isEmpty;
  }
}
