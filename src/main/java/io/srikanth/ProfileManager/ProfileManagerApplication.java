package io.srikanth.ProfileManager;

import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.srikanth.ProfileManager.connection.DataStaxAstraProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class ProfileManagerApplication {

    public static void main(String[] args) {
	SpringApplication.run(ProfileManagerApplication.class, args);
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
	Path bundle = astraProperties.getSecureConnectBundle().toPath();
	return builder -> builder.withCloudSecureConnectBundle(bundle);
    }

}
