package dk.cpr.dar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DummyServicesApplication implements ApplicationListener<ContextRefreshedEvent> {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${server.port}")
  private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(DummyServicesApplication.class, args);
	}

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    Environment environment = contextRefreshedEvent.getApplicationContext().getEnvironment();

    logger.info("*** Component starting: {}-{}",  environment.getProperty("info.build.artifact"),environment.getProperty("info.build.version"));

    logger.info("DummyServicesApplication started on port:"+ serverPort);
  }
}
