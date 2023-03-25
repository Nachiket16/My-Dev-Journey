package com.mydevjourney.domaincrawler;

import java.util.Objects;

public class Domain {

  String domain;
  String create_date;
  String update_date;
  String country;
  boolean isDead;
  String A;
  String NS;
  String CNAME;
  String MX;
  String TXT;

  public Domain(){

  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Domain domain1 = (Domain) obj;
    return isDead == domain1.isDead && Objects.equals(domain, domain1.domain) && Objects.equals(create_date,obj);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domain, create_date, update_date, country, isDead, A, NS, CNAME, MX, TXT);
  }
}
