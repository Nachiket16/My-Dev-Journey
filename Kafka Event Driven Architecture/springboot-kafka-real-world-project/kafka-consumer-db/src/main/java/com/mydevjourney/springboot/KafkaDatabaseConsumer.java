package com.mydevjourney.springboot;

import com.mydevjourney.springboot.entity.WikimediaData;
import com.mydevjourney.springboot.repository.WikimediaDataRepo;
import java.sql.Blob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

  private WikimediaDataRepo wikimediaDataRepo;

  public KafkaDatabaseConsumer(WikimediaDataRepo wikimediaDataRepo) {
    this.wikimediaDataRepo = wikimediaDataRepo;
  }

  @KafkaListener(
      topics = "wikimedia_recentchage",
      groupId = "myGroup"
  )
  public void consume(String eventMessage){
    LOGGER.info(String.format("Event Message Received :->  %s", eventMessage));
    WikimediaData wikimediaData = new WikimediaData();
    wikimediaData.setWikiEvenetData(eventMessage);

    wikimediaDataRepo.save(wikimediaData);


  }


}
