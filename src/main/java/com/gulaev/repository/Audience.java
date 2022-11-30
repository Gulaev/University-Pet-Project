package com.gulaev.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "audiences")
@Data
public class Audience implements Model {

  @Id
  @Column(name = "audience_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer audienceId;

  @Column(name = "audience_number")
  private Integer audienceNumber;

  @Column(name = "floor")
  private Integer floor;

  @Column(name = "number_of_seats")
  private Integer numberOfSeats;

  @Column(name = "interactive_whiteboard")
  private boolean interactiveWhiteboard;

  @Column(name = "is_empty")
  private boolean isEmpty;

  public Audience() {}

  public Audience(Integer audienceNumber, Integer floor, Integer numberOfSeats,
      boolean interactiveWhiteboard, boolean isEmpty) {
    this.audienceNumber = audienceNumber;
    this.floor = floor;
    this.numberOfSeats = numberOfSeats;
    this.interactiveWhiteboard = interactiveWhiteboard;
    this.isEmpty = isEmpty;
  }
}
