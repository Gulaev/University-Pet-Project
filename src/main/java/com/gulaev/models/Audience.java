package com.gulaev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Data;
import org.hibernate.annotations.Table;

@Entity
@Table(appliesTo = "audiences")
@Data
public class Audience implements Model {

  @Id
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
