package com.gulaev.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

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

// ManyToOne
  @OneToMany(mappedBy = "audienceId", fetch = FetchType.LAZY)
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  private Set<Lesson> lessons;

  @OneToMany(mappedBy = "audience", fetch = FetchType.LAZY)
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  private Set<Speciality> specialities;

  public Audience() {}

  public Audience(Integer audienceNumber, Integer floor, Integer numberOfSeats,
      boolean interactiveWhiteboard, boolean isEmpty, Set<Lesson> lessons,
      Set<Speciality> specialities) {
    this.audienceNumber = audienceNumber;
    this.floor = floor;
    this.numberOfSeats = numberOfSeats;
    this.interactiveWhiteboard = interactiveWhiteboard;
    this.isEmpty = isEmpty;
    this.lessons = lessons;
    this.specialities = specialities;
  }
}
