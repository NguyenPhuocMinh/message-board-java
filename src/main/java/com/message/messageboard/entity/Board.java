package com.message.messageboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "boards")
public class Board implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(length = 1000, nullable = false)
  private String description;

  @Column(nullable = false)
  private Date registerDate;

  /**
   * FILTER
   */
  private boolean deleted = false;
  private Date createdAt = new Date();
  private String createdBy = "SYSTEM";
  private Date updatedAt;
  private String updatedBy = "SYSTEM";
}
