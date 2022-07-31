package com.codehunter.springmvcjpathymeleafliquibasepractice.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User implements Serializable {
  @Id
  @GeneratedValue
  private Integer id;

  private String name;


}
