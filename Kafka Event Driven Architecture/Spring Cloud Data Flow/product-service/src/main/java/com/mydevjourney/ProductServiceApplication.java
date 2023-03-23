package com.mydevjourney;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.messaging.*;

@SpringBootApplication
public class ProductServiceApplication {

	Logger LOGGER = LoggerFactory.getLogger(ProductServiceApplication.class);

	@Bean
	@InboundChannelAdapter(poller = @Poller(fixedDelay = "10000",maxMessagesPerPoll = "1"))
	public MessageSource<List<Product>> addProducts(){
		List<Product> products	= Stream.of(
				new Product(100,"Laptop",84000),
				new Product(101,"Monitor 4k",32000))
				.collect(Collectors.toList());
		LOGGER.info("Products : {}",products);
		return ()-> MessageBuilder.withPayload(products).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
