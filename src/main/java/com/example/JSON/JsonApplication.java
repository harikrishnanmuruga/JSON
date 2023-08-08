package com.example.JSON;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
@Configuration

public class JsonApplication {
	public class JacksonConfig {
		@Bean
		public Jackson2ObjectMapperBuilder objectMapperBuilder() {
			Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
			builder.modulesToInstall(new JodaModule());
			return builder;
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(JsonApplication.class, args);
	}

}
