package io.srikanth.ProfileManager;

import org.springframework.context.annotation.Bean;

import com.datastax.oss.driver.api.core.CqlSession;

public class AppConfig {
    public @Bean CqlSession session( ) {
	return CqlSession.builder().withKeyspace("profilemanager").build();
    }
}
