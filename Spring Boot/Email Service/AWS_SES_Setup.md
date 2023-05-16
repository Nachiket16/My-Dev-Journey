# Implementation Design 

Rather than creating the service and triggering the email will take the time.   
As each service will wait till it's complition.   
We can create an event and do the rest of logic, event listener will capture the info and publish the email.

- Create a service .
- Create a event publisher inside the service to publish the data.
- Event listerner will capture the data by event publisher.
- Trigger the Email service of your choice and create the mail body using the event listener body.

> AWS SES Guide   
> https://stackabuse.com/guide-to-simple-email-service-aws-ses-with-spring-boot-and-spring-cloud/

> Automate Emails Using Spring Application Events & Apache FreeMarker   
> https://blog.devgenius.io/automate-emails-using-spring-application-events-apache-freemarker-eeccf0c56b75

_ _ _
# Spring Boot Setup

### application.properties
* cloud.aws.credentials.access-key= ACESSKEYINALLCAPS
* cloud.aws.credentials.secret-key= aklsjfdlkasdflkajsdflkdummy
* cloud.aws.region.static=us-east-1   (case & format sesetive)

### XYZEvent
``` JAVA
@Getter
public class XYZEvent extends ApplicationEvent {
    private BigDecimal amount;
    private String Id;

    public XYZEvent(OfflineRefundRequest source) {
        super(source);
        this.amount = source.getAmount();
        this.ddrId = source.getId();
    }
}
```
### Event Listener
``` JAVA
@Slf4j
@Component
public class XYZEmailListener {

    @Autowired private XYZService XYZService;

    @Async
    @EventListener(XYZEvent.class)
    public void refundEmail(XYZEvent event) throws IOException {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("nachiket.khule@gmail.com");
        simpleMailMessage.setTo("john.doe@xyz.com");
        simpleMailMessage.setSubject("Flipkart Refund Request");
        simpleMailMessage.setText(
                "Received Refund requested from Flipkart with Amount "
                        + event.getAmount()
                        + " for"
                        + " customer ID : "
                        + event.getId());
        refundService.sendMessage(simpleMailMessage);
    }
}
``` 
### Mail Config (AWS-SES)
``` xml
 <dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>io.awspring.cloud</groupId>
			<artifactId>spring-cloud-aws-dependencies</artifactId>
			<version>2.3.0</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>

<dependency>
	<groupId>io.awspring.cloud</groupId>
	<artifactId>spring-cloud-starter-aws-ses</artifactId>
</dependency>
```
``` JAVA
package com.mintifi.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class MailConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
    }
}
```


### Service
```JAVA
@Service
@Transactional
public class XYZService {
  
    @Autowired private MailSender mailSender;
    @Autowired private ApplicationEventPublisher applicationEventPublisher;
  
    public Optional<RefundResponse> saveRequest(Request request) {
        // Serice logic -JPA-Mapper-etc
              applicationEventPublisher.publishEvent(new RefundEvent(offlineRefundRequest));
    }
    public void sendMessage(SimpleMailMessage simpleMailMessage) {
        this.mailSender.send(simpleMailMessage);
    }
}
```

