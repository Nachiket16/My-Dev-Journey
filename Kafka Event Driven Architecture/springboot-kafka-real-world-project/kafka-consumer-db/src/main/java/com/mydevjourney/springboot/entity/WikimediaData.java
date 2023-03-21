package com.mydevjourney.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.sql.Blob;
import lombok.Data;

@Entity
@Table(name = "wikimedia_recentchange")
@Data
public class WikimediaData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Lob
  @Column(name="wikiEvenetData", length=512)
  private String wikiEvenetData;


}
