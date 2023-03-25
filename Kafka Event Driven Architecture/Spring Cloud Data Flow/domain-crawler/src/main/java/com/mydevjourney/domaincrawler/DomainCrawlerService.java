package com.mydevjourney.domaincrawler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class DomainCrawlerService {

  public void crawl(String name) {

    WebClient.create()
        .get()
        .uri("https://api.domainsdb.info/v1/domains/search?domain="+name+"&zone=com")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(DomainList.class);
  }
}
