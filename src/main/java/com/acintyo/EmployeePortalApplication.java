package com.acintyo;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
@SpringBootApplication(exclude= {JacksonAutoConfiguration.class})

public class EmployeePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePortalApplication.class, args);
	}
	@Bean
	public Jackson2ObjectMapperBuilder createJackson2ObjectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder().serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss"))).deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
	}
	


}
