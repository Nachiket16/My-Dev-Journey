package com.devjourney.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChagesProducer  {

  private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChagesProducer.class);

  private KafkaTemplate<String, String> kafkaTemplate;


  public WikimediaChagesProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage() throws InterruptedException {
    String topic = "wikimedia_recentchage";

    //TO read real time stream data from wikimedia, we use event source

    EventHandler eventHandler = new WikimediaChagesHandler(kafkaTemplate,topic);
    String url = "https://stream.wikimedia.org/v2/stream/mediawiki.recentchange";

    EventSource.Builder builder = new EventSource.Builder(eventHandler,URI.create(url));
    EventSource eventSource = builder.build();

    eventSource.start();

    TimeUnit.MINUTES.sleep(10);

  }

}
