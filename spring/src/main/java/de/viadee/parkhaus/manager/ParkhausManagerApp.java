package de.viadee.parkhaus.manager;

import de.viadee.parkhaus.manager.config.ParkhausConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ParkhausConfig.class)
public class ParkhausManagerApp {
   public static void main(String[] args) {
      SpringApplication.run(ParkhausManagerApp.class, args);
   }
}
